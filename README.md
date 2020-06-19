
# TravelUP 
BDMA joint project (BDM and SDM). 

This repository includes the code of the different elements of our architecture.  We used Python to scrape data, Spark for data processing and Jena API as RDF builder.

The folders are organised as follow:

* [tripadvisor_scraper](https://github.com/balloonka135/travelup_knowledge_graph/tree/master/tripadvisor_scraper "tripadvisor_scraper") with the data extraction script for Tripadvisor data source
* [tourpedia_knowledge_graph](https://github.com/balloonka135/travelup_knowledge_graph/tree/master/tourpedia_knowledge_graph) with the ABox creation for the TourPedia data source
* [tripadvisor_knowledge_graph](https://github.com/balloonka135/travelup_knowledge_graph/tree/master/tripadvisor_knowledge_graph "tripadvisor_knowledge_graph") with the ABox creation for the Tripadvisor data source
* [twitter_knowledge_graph](https://github.com/balloonka135/travelup_knowledge_graph/tree/master/twitter_knowledge_graph "twitter_knowledge_graph") with the ABox creation for the Twitter data source
* [tripadvisor_JSON_processing](https://github.com/balloonka135/travelup_knowledge_graph/tree/master/tripadvisor_JSON_processing "tripadvisor_JSON_processing") with the script to process the JSON data collected into a CSV format for Jena API
* [TravelupAndroidPrototype](https://github.com/balloonka135/travelup_knowledge_graph/tree/master/TravelupAndroidPrototype "TravelupAndroidPrototype") with the mobile app prototype
* [Spark](https://github.com/balloonka135/travelup_knowledge_graph/tree/master/Spark "Spark") with the label extractor
* [projectSparkStream](https://github.com/balloonka135/travelup_knowledge_graph/tree/master/projectSparkStream "projectSparkStream") with the Twitter streaming API
* [hdfs](https://github.com/balloonka135/travelup_knowledge_graph/tree/master/hdfs "hdfs") with the script to load the HDFS
