import json

import requests
from bs4 import BeautifulSoup
from service.provider.url_provider import BASE_URL, LIST_OF_SECONDARY_SKILLS_URL
from service.util.clean_utils import clean

from service.util.url_utils import compose_url


def extract_secondary_skills_from_table(table, headers):
    secondary_skills = []
    for row in table.find_all('tr')[1:]:
        secondary_skill = {}
        cols = row.find_all('td')

        for index, header in enumerate(headers):
            print(cols[index].text)
            cell_value = cols[index].get_text()
            secondary_skill[header] = clean(cell_value).strip()

        # add links
        secondary_skill['selfLink'] = BASE_URL + cols[0].find("a")['href']

        secondary_skills.append(secondary_skill)
    return secondary_skills

def scrape_secondary_skills():
    resp = requests.get(compose_url(LIST_OF_SECONDARY_SKILLS_URL))
    resp.raise_for_status()
    soup = BeautifulSoup(resp.text, "html.parser")

    table = soup.find_all("table")[4]

    headers = ['name','allClasses','mightClasses','magicClasses','goodClasses','neutralClasses','evilClasses']

    return extract_secondary_skills_from_table(table, headers)

def main():
    filename = '../../resources/data/secondary_skills.json'

    secondary_skills = scrape_secondary_skills()

    with open(filename, 'w') as f:
        json.dump(secondary_skills, f, indent=4)  # indent=4 for pretty-printing (optional)

    print(f"Data successfully saved to {filename}")

if __name__ == "__main__":
    main()