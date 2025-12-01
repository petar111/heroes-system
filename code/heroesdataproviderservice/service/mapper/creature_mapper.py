import json


def map_creature(raw_creature):
    return {
          'name':raw_creature['name'],
          'description':raw_creature['description'],
          'hitpoints':int(raw_creature['health']),
          'battleCapacities':[
            {
              'battleTypeId':1,
              'attackMin':int(raw_creature['attack']) + int(raw_creature['minimumDamage']),
              'attackMax':int(raw_creature['attack']) + int(raw_creature['maximumDamage']),
              'defenceMin':0,
              'defenceMax':int(raw_creature['defense']),
            }
          ]
        }


def main():
    with open('../../resources/data/creatures/creatures.json', 'r') as file:
        raw_creatures = json.load(file)

    creatures = []
    for raw_creature in raw_creatures:
        creatures.append(map_creature(raw_creature))

    filename = '../../resources/data/creatures/creatures_mapped.json'

    with open(filename, 'w') as f:
        json.dump(creatures, f, indent=4)  # indent=4 for pretty-printing (optional)

    print(f"Data successfully saved to {filename}")


if __name__ == "__main__":
    main()


# {
#   "id":1,
#   "name":"Knight",
#   "description":""
#   "hitpoints":80,
#   "battleCapacities":[
#     {
#       "id":1,
#       "battleTypeId":1,
#       "attackMin":20,
#       "attackMax":27,
#       "defenceMin":30,
#       "defenceMax":32,
#       "entitiyId":1
#
#     },
#     {
#       "id":2,
#       "battleTypeId":2,
#       "attackMin":0,
#       "attackMax":0,
#       "defenceMin":40,
#       "defenceMax":50,
#       "entitiyId":1
#     }
#   ]
# }