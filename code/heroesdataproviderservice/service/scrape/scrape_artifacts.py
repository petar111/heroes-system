import json

import requests
from bs4 import BeautifulSoup

from service.provider.url_provider import BASE_URL, LIST_OF_ARTIFACTS_URL
from service.util.clean_utils import clean
from service.util.clean_utils import to_camel_case
from service.util.url_utils import compose_url


def extract_artifacts_from_table(table, headers):
    artifacts = []
    for row in table.find_all('tr')[1:]:
        artifact = {}
        cols = row.find_all('td')

        for index, header in enumerate(headers):
            cell_value = ''

            if header == 'Expansion Pack':
                cell_value = clean(cols[index].get_text()).strip()
                if not cell_value:
                    cell_value = cols[index].find('a')['title']

            if not cell_value:
                cell_value = cols[index].get_text()

            artifact[to_camel_case(header)] = clean(cell_value).strip()

        # add links
        artifact['selfLink'] = BASE_URL + cols[0].find("a")['href']

        artifacts.append(artifact)
    return artifacts

def scrape_artifacts():
    resp = requests.get(compose_url(LIST_OF_ARTIFACTS_URL))
    resp.raise_for_status()
    soup = BeautifulSoup(resp.text, "html.parser")
        
    # Combat Damage artifacts
    table = soup.find("table")
    headers = ['Name','Expansion Pack','Slot','Class','Cost','Effect','Combination']

    return extract_artifacts_from_table(table, headers)

def main():
    filename = '../../resources/data/artifacts.json'

    artifacts = scrape_artifacts()

    with open(filename, 'w') as f:
        json.dump(artifacts, f, indent=4)  # indent=4 for pretty-printing (optional)

    print(f"Data successfully saved to {filename}")

if __name__ == "__main__":
    main()