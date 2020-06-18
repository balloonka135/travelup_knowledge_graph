import json
import numpy as np
import pandas as pd
import os

JSON_FILES_PATH = 'input/'
TAGS_FILE = 'tags_map.json'
LOCATIONS_FILE = 'attractions_500.json'
REVIEWS_FOLDER_PATH = 'input/reviews/'
OUTPUT_PATH = 'output'


def create_tags_csv(path, file):
    """ Create tags and has_tag relationship csv file with needed data for the ontology from the json file"""
    tag_map = {}
    location_tags_map = {}

    with open(path + file) as f:
        tags_file = json.load(f)

        for i, item in enumerate(tags_file):
            for v in item:
                location_tags = []
                location_id = v.split("_")[1]
                for z in tags_file[i][str(v)][0]:
                    try:
                        tag = z.get('tagName', None)
                    except KeyError:
                        pass

                    if tag not in tag_map:
                        tag_map[tag] = len(tag_map)
                    location_tags.append(tag)
                location_tags_map[location_id] = location_tags

    df_tag_map = {"tag_id": [], "name": []}
    df_has_tag_map = {"location_id": [], "tag_id": []}

    np.random.seed(1)
    ids = np.random.randint(low=1e9, high=1e10, size=len(tag_map))
    for i, (tag, old_id) in enumerate(tag_map.items()):
        new_id = ids[i]
        tag_map[tag] = new_id
        df_tag_map["name"].append(tag)
        df_tag_map["tag_id"].append(new_id)

    for location, tags in location_tags_map.items():
        for tag in tags:
            df_has_tag_map["location_id"].append(location)
            df_has_tag_map["tag_id"].append(tag_map[tag])

    return pd.DataFrame(df_tag_map), pd.DataFrame(df_has_tag_map)


def create_locations_csv(path, file):
    """ Create locations csv file with needed data for the ontology from the json file"""
    with open(path + file) as f:
        att = json.load(f)
    locations = att[0]['data']['attractionsResponse'][0]['attractions']
    locName = []
    locID = []
    locURL = []
    totReviews = []
    avgReviews = []
    rank = []
    price = []

    for i, item in enumerate(locations):
        locName.append(locations[i]['location']['name'])
        locID.append(locations[i]['location']['locationId'])
        locURL.append(locations[i]['location']['url'])
        totReviews.append(locations[i]['totalReviews'])
        avgReviews.append(locations[i]['avgReviews'])
        rank.append(locations[i]['ranking']['value'])
        if locations[i]['minTicketPriceInfo'] is not None:
            price.append(locations[i]['minTicketPriceInfo']['price'])
        else:
            price.append('Free')

        loc_df = pd.DataFrame()
        loc_df['locationID'] = locID
        loc_df['Names'] = locName
        loc_df['url'] = locURL
        loc_df['ranking'] = rank
        loc_df['reviewsAVG'] = avgReviews
        loc_df['totalReviews'] = totReviews
        loc_df['price'] = price

    return loc_df


def create_reviews_csv(path):
    """ Create review and about_attraction relationship csv file with needed data for the ontology from the json file"""

    attID = []
    placeType = []
    reviewList = []
    json_files = [pos_json for pos_json in os.listdir(path) if pos_json.endswith('.json')]
    for i in json_files:
        with open(path + i) as f:
            rev = json.load(f)
            attID.append(rev[0]['locationId'])
            placeType.append(rev[0]['placeType'])
            reviewList.append(rev[0]['reviewListPage']['reviews'])

    reviews_df = pd.DataFrame()
    reviews_df['locationID'] = attID
    reviews_df['placeType'] = placeType
    reviews_df['reviewListPage'] = reviewList
    Idx = reviews_df.set_index(['locationID', 'placeType']).reviewListPage.apply(pd.Series).stack().index
    reviews_df = pd.DataFrame(reviews_df.set_index(['locationID', 'placeType'])
                              .reviewListPage.apply(pd.Series)
                              .stack().values.tolist(), index=Idx).reset_index()
    del reviews_df['level_2']
    del reviews_df['language']
    reviews_df.rename(columns={'id': 'reviewID'}, inplace=True)
    reviews_df.columns = reviews_df.columns.str.strip()
    reviews_df = reviews_df.replace(r'\n', ' ', regex=True)
    reviews_df = reviews_df.replace(r';', ',', regex=True)

    # about_attraction Relationship Dataframe
    about_location = pd.DataFrame()
    about_location = pd.DataFrame(reviews_df[['locationID', 'reviewID']].copy())

    return reviews_df, about_location


def main():
    tags, has_tags = create_tags_csv(JSON_FILES_PATH, TAGS_FILE)
    tags.to_csv(OUTPUT_PATH+'/tags.csv', encoding='utf-8', index=False)
    has_tags.to_csv(OUTPUT_PATH+'/has_tags.csv', encoding='utf-8', index=False)

    attractions = create_locations_csv(JSON_FILES_PATH, LOCATIONS_FILE)
    attractions.to_csv(OUTPUT_PATH+'/attractions.csv', encoding='utf-8', index=False)

    reviews, about_location = create_reviews_csv(REVIEWS_FOLDER_PATH)
    reviews.to_csv(OUTPUT_PATH+'/reviews.csv', sep=';', index=False, encoding='utf-8')
    about_location.to_csv(OUTPUT_PATH+'/about_attraction.csv', sep=';', index=False, encoding='utf-8')

    return


if __name__ == "__main__":
    # execute only if run as a script
    main()
