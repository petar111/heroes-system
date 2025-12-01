import json
import re

import requests
from bs4 import BeautifulSoup

from service.provider.url_provider import BASE_URL, LIST_OF_CREATURES_URL
from service.util.clean_utils import clean
from service.util.clean_utils import to_camel_case
from service.util.url_utils import compose_url


def scrape_creatures():
    resp = requests.get(compose_url(LIST_OF_CREATURES_URL))
    resp.raise_for_status()
    soup = BeautifulSoup(resp.text, "html.parser")
    
    

    # Find the main creatures table
    table = soup.find("table")
    if not table:
        raise RuntimeError("Could not find creatures table on page")
        
    # Extract headers
    headers = []
    for th in table.find_all("th"):

        span = th.find("span")
        if span is not None and span.has_attr('title'):
            creatures_header = span['title']
        else:
            creatures_header = th.text

        final_headers = re.sub(r'[^a-zA-Z0-9 ]', '', creatures_header)

        if final_headers == 'AI Value':
            final_headers = 'AI_Value'

        if final_headers:
            headers.append(final_headers)

    creatures = []
    for row in table.find_all("tr")[2:]:
        cols = row.find_all("td")

        spans = map(lambda c: c.find("span"), cols)
        spans = filter(lambda c: c is not None, spans)
        spans = set(spans)
        span_list = list(map(lambda s: {"title": s.get('title', 'Unknown'), "text": clean(s.text).strip()}, spans))
        span_list = list(filter(lambda s: s['title'] and s['title'] != 'Unknown', span_list))
        span_list = list(filter(lambda s: s['text'] and s['text'] != 'Unknown', span_list))

        creature = {}

        for header in headers:

            cell_value = ''

            if header == 'Name':
                td_name = cols[0]
                span_name = td_name.find("span")
                cell_value = span_name.text.strip()
            if header == 'Town':
                td_town = cols[1]
                a_town = td_town.find("a")
                cell_value = a_town['title']
            if header == 'Cost':
                td_cost = cols[11]
                cell_value = td_cost.text.split(' ')[0]
            if header == 'Special':
                td_special = cols[13]
                cell_value = clean(td_special.get_text())


            if not cell_value:
                span = next((s for s in span_list if s['title'] == header), {'title': 'Unknown', 'text': 'Unknown'})
                cell_value = span['text']

            creature[to_camel_case(header)] = clean(cell_value).strip()


        #add links
        creature['selfLink'] = BASE_URL + cols[0].find("a")['href']
        creature['townLink'] = BASE_URL + cols[1].find("a")['href']

        creatures.append(creature)

    return creatures


def post_process(creature):
    print(f"Process creature {creature['name']}. - START")

    resp = requests.get(creature['selfLink'])
    resp.raise_for_status()
    soup = BeautifulSoup(resp.text, "html.parser")

    text_contents = (soup.find(id="mw-content-text").find('div')
                     .find_all(['p'], recursive=False))

    creature['homm3Description'] = clean(text_contents[0].text).strip()
    creature['description'] = clean(text_contents[1].text).strip()
    creature['additionalInfo'] = list(map(lambda p: clean(p.text).strip(), text_contents[2:]))

    print(f"Process creature {creature['name']}. - END")


def main():
    filename = '../../resources/data/creatures/creatures.json'

    creatures = scrape_creatures()
    print(f"Scraped {len(creatures)} creatures.")

    for creature in creatures:
        post_process(creature)

    with open(filename, 'w') as f:
        json.dump(creatures, f, indent=4)  # indent=4 for pretty-printing (optional)

    print(f"Data successfully saved to {filename}")

if __name__ == "__main__":
    main()