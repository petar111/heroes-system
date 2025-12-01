from service.provider.url_provider import BASE_URL, INDEX_URL


def compose_url(resource):
    return BASE_URL + INDEX_URL + resource