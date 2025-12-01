
file_path = "../../resources/links.txt"

def provide_base_url():
    with open(file_path, 'r') as file:
        for line in file:
            if line.startswith('BASE_URL'):
                return line.split('=')[1].strip()
        return None


BASE_URL = provide_base_url()
INDEX_URL = "/index.php"

LIST_OF_ARTIFACTS_URL = "/List_of_artifacts"
LIST_OF_CREATURES_URL = "/List_of_creatures"
LIST_OF_HEROES_URL = "/List_of_heroes"
LIST_OF_SPELLS_URL = "/List_of_spells"
LIST_OF_SECONDARY_SKILLS_URL = "/Secondary_skill"