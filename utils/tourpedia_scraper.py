import requests
import csv
import json


place_endpoint = 'http://tour-pedia.org/api/getPlaceDetails?id={id}'
review_endpoint = 'http://tour-pedia.org/api/getReviewsByPlaceId?placeId={id}'


categories = {
    'attraction': '1',
    'accommodation': '2',
    'poi': '3',
    'restaurant': '4'
}


def place_scraper():
    location_idx = 0

    with open('tourpedia_datasets/frame.csv', 'r') as f_ids:
        csv_reader = csv.reader(f_ids, delimiter=',')
        for row in csv_reader:
            location_idx += 1
            place_id = row[0]
            r = requests.get(place_endpoint.format(id=place_id))
            response = json.loads(r.text)

            # get all keys from response
            _id = response.get('id')
            name = response.get('name')
            address = response.get('address')
            location = response.get('location')
            lat = response.get('lat')
            lng = response.get('lng')
            description = str(response.get('description'))
            phone_number = response.get('phone_number')
            international_phone_number = response.get('international_phone_number')
            website = response.get('website')
            icon = response.get('icon')
            category = response.get('category')
            sub_category = response.get('subCategory')
            statistics = str(response.get('statistics'))
            polarity = response.get('polarity')

            if name:
                name = name.encode('utf-8')
            if address:
                address = address.encode('utf-8')
            if location:
                location = location.encode('utf-8')
            if description:
                description = description.encode('utf-8')
            if phone_number:
                phone_number = phone_number.encode('utf-8')
            if international_phone_number:
                international_phone_number = international_phone_number.encode('utf-8')
            if website:
                website = website.encode('utf-8')
            if icon:
                icon = icon.encode('utf-8')
            if sub_category:
                sub_category = sub_category.encode('utf-8')

            # write places info into places.csv
            with open('data/places.csv', 'a') as f_places:
                writer = csv.writer(f_places, delimiter=',')
                row = [
                    _id, name, description, phone_number, international_phone_number,
                    website, icon, sub_category, statistics, polarity
                ]
                writer.writerow(row)


            # write location info into location.csv
            with open('data/locations.csv', 'a') as f_locs:
                writer = csv.writer(f_locs, delimiter=',')
                row = [location_idx, address, location, lat, lng]
                writer.writerow(row)

            # check type of category and write place id and category id into has_category.csv
            with open('data/has_category.csv', 'a') as f_hc:
                writer = csv.writer(f_hc, delimiter=',')
                category_id = categories[category]
                row = [place_id, category_id]
                writer.writerow(row)

            # write to located_at.csv corresponding place id and location id
            with open('data/located_at.csv', 'a') as f_la:
                writer = csv.writer(f_la, delimiter=',')
                row = [place_id, location_idx]
                writer.writerow(row)


def review_scraper():
    review_idx = 0

    with open('tourpedia_datasets/frame.csv', 'r') as f_ids:
        csv_reader = csv.reader(f_ids, delimiter=',')
        for row in csv_reader:
            place_id = row[0]
            r = requests.get(review_endpoint.format(id=place_id))
            response = json.loads(r.text)

            # check for empty list
            if response:
                # iterate over all dicts in response
                # from each get all keys
                for item_dict in response:
                    review_idx += 1

                    language = item_dict.get('language')
                    polarity = item_dict.get('polarity')
                    rating = item_dict.get('rating')
                    source = item_dict.get('source')
                    text = item_dict.get('text')
                    if text:
                        text = text.encode('utf-8')
                    time = item_dict.get('time')
                    words_count = item_dict.get('wordsCount')
                    details = item_dict.get('details')


                    # write review data to reviews.csv
                    with open('data/reviews.csv', 'a') as f_rew:
                        writer = csv.writer(f_rew, delimiter=',')
                        row = [
                            review_idx, language, polarity, rating, source,
                            text, time, words_count, details 
                        ]
                        writer.writerow(row)

                    # write is_about.csv corresponding place id and review id
                    with open('data/is_about.csv', 'a') as f_ia:
                        writer = csv.writer(f_ia, delimiter=',')
                        row = [review_idx, place_id]  
                        writer.writerow(row)  


# place_scraper()
review_scraper()




