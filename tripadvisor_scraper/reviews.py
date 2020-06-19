import json
import time

import requests

locationIds = [190166, 190162, 191052, 190824, 190629, 271009, 190164, 246171, 1192272, 257527, 191048, 501696, 2359212,
               244211, 531598, 254723, 295879, 246511, 319140, 190637, 254718, 591343, 3224257, 251997, 190624, 190165,
               244210, 313695, 208597, 2717850, 244194, 195113, 2015831, 271008, 2165013, 3240045, 243171, 246512,
               191047, 208598, 265458, 2223690, 2394734, 4609101, 246168, 3468814, 8398821, 190163, 244200, 2645511,
               257528, 7785899, 195252, 2223636, 7175292, 3782382, 2067434, 1893220, 195124, 6453867, 4106898, 6417067,
               10592746, 592217, 244195, 15056638, 4945354, 7052313, 2272433, 4660265, 244199, 8564136, 240755, 4469233,
               7132622, 6495421, 3139265, 3954329, 244821, 4722328, 7208916, 4027803, 265457, 244218, 8489665, 2235343,
               3467712, 254719, 8036442, 2690761, 2366700, 240766, 246190, 2223604, 313685, 313686, 243174, 1809020,
               244208, 7021032, 7810574, 7820170, 7694897, 2485623, 619690, 7175296, 12847550, 6206152, 12606976,
               190623, 6401014, 2623636, 190634, 7914040, 6034377, 597513, 8820217, 2328556, 3410795, 7984164, 10230999,
               7363048, 244855, 240500, 2223655, 4123720, 8865719, 6214229, 7102217, 2625535, 3360600, 13970378, 591146,
               4597992, 7687719, 1553048, 7981033, 7148963, 4752371, 531612, 8782951, 12674080, 7804964, 244201,
               8866286, 4414261, 13154025, 11793916, 7376615, 8702700, 6116743, 7106610, 4310632, 12352056, 8646729,
               4764185, 12661256, 10467498, 10223979, 6116899, 2321733, 2487738, 4346018, 4960182, 2577943, 10538518,
               8692632, 244202, 2223602, 528113, 3929138, 6942942, 7161481, 8742797, 244903, 7190997, 6847808, 9566855,
               2449596, 11729063, 7152939, 9882566, 5542130, 244206, 10365279, 6517023, 244197, 10296722, 8434367,
               1191660, 10023525, 4597443, 9582536, 10713149, 4002311, 8471899, 8476175, 4234451, 3226916, 2295948,
               10538454, 11930470, 3270333, 313693, 8614393, 2531489, 3467639, 10691974, 8736807, 6412469, 8332676,
               658750, 243179, 8670722, 10215257, 3139274, 6434373, 10340402, 8376916, 14059148, 2223627, 4880672,
               1192310, 10535133, 13447182, 8614069, 8797192, 12301106, 10256990, 6857198, 313697, 7008573, 1192270,
               8786845, 7712595, 11932628, 10491280, 592191, 8563289, 10093192, 8807461, 313684, 523834, 3367732,
               1418571, 6500682, 240504, 9805865, 8734190, 10819859, 15668683, 10020538, 11737112, 8862070, 4498104,
               13998749, 9859074, 10491414, 10171290, 11964151, 15208082, 3480069, 8857417, 12352280, 10175605, 1418568,
               8862038, 10600103, 8820333, 10043419, 6499629, 8610971, 8598951, 2235346, 17561580, 7735950, 9571023,
               9821686, 10538407, 8610896, 8526772, 10713220, 8544847, 9706593, 2223579, 10086735, 11807755, 8614075,
               2218960, 11930450, 8807625, 8677305, 7390331, 14765486, 8422039, 7788447, 244897, 9764670, 531596,
               6673955, 10538617, 3678721, 244902, 7055716, 15087184, 10065909, 12643730, 17639374, 244904, 10231433,
               10230877, 1976641, 9570223, 10682799, 10256497, 1606662, 1467904, 2223526, 10592815, 3518832, 11773948,
               7002028, 8685987, 9705588, 10445408, 8807616, 8692638, 10021405, 13003921, 4937053, 14004883, 14001384,
               8273053, 7349560, 13289877, 8321696, 10459206, 10538430, 8820360, 8807630, 9986707, 8627151, 8498163,
               240501, 12622440, 10264171, 11930469, 8321570, 11810740, 8627215, 10065946, 6727157, 10592716, 19455163,
               10209665, 10296861, 8685983, 10592599, 14287984, 16781808, 2669781, 10592702, 14055509, 4752027,
               15053809, 244860, 17469356, 12544426, 8089839, 8736833, 17750898, 14048734, 8685995, 13738059, 7149102,
               4466782, 11548121, 12920026, 240760, 8308723, 8807615, 4758992, 1988114, 12495392, 13289883, 10557113,
               244203, 8807498, 11930476, 12682432, 4688353, 13546899, 17593156, 3477513, 11930453, 9764642, 9727576,
               9465523, 14188996, 8807499, 13543812, 13531380, 13962782, 8630465, 11932627, 4093062, 9750762, 11930433,
               9884939, 12879602, 11930464, 10223737, 10713504, 11932643, 12860132, 12056104, 10670300, 9802114,
               1493833, 3820365, 15086183, 11930472, 9570964, 12488986, 10089782, 15532136, 15030177, 12703751, 2223619,
               14142348, 8807492, 10077498, 16801122, 10223755, 10060363, 12228778, 11958463, 9851986, 12583627,
               8093226, 9608985, 4556759, 9992984, 10214244, 17766742, 8677533, 15582053, 10491252, 4603634, 11932634,
               14802984, 15337720, 4888437, 8321737, 9730001, 9802260, 10022418, 244930, 10696147, 10682802, 10538541,
               9570798, 9597197, 9802110, 6226814, 16658729, 11930439, 8612214, 14141952, 240763, 8820156, 10065947,
               244913, 244843, 15663133, 12961025, 10713522, 10065872, 8281216, 10198073, 8807479, 9568162, 15337789,
               244858, 7391470, 9997409, 10296763, 10538499, 15209009, 11930430, 15511804, 1747997, 4556731, 8145612,
               10676069, 10426987, 9454021, 17571145, 13201762, 11932639]

headers = {
    'content-type': 'application/json',
    'cookie': 'TADCID=UaKZutBdU1HzVWV9ABQCjnFE8vTET66GHuEzPi7KfV4mqTBsxvK-_VffcnzAE_CXidWZkBVt-I4i0MgFOeZYVbaJ88Rwblbfm4E; TAUnique=%1%enc%3AMmnSYK198ZhctJEt51MSoraJQ6qD%2FwX7T9rYwHfyJoo2jHwltRJPGQ%3D%3D; TASSK=enc%3AABHg3jA1SyeSJf80xTaGrNVF5lcRSRxMYxSYgFuzMrQWhakMRigHKI1DfuX3PM%2BBSZEVozFj8TtJgGXKr2jqxTJ2kjcVYv1NOqODmX6JcMs7TjtmewrZIwBbqds0NI0YTg%3D%3D; ServerPool=B; PMC=V2*MS.76*MD.20200615*LD.20200615; TART=%1%enc%3AXLSRLedTEqLvUPoJNmz67cBGBNkO17wNfer%2Btk%2BoO11wsWIjnLZ8DyAC3xolzDqgyx9HjuV9fk0%3D; TATravelInfo=V2*A.2*MG.-1*HP.2*FL.3*RS.1; CM=%1%PremiumMobSess%2C%2C-1%7Ct4b-pc%2C%2C-1%7CRestAds%2FRPers%2C%2C-1%7CRCPers%2C%2C-1%7CWShadeSeen%2C%2C-1%7CTheForkMCCPers%2C%2C-1%7CHomeASess%2C%2C-1%7CPremiumMCSess%2C%2C-1%7CCrisisSess%2C%2C-1%7CUVOwnersSess%2C%2C-1%7CRestPremRSess%2C%2C-1%7CRepTarMCSess%2C%2C-1%7CCCSess%2C%2C-1%7CCYLSess%2C%2C-1%7CPremRetPers%2C%2C-1%7CViatorMCPers%2C%2C-1%7Csesssticker%2C%2C-1%7CPremiumORSess%2C%2C-1%7Ct4b-sc%2C%2C-1%7CRestAdsPers%2C%2C-1%7CMC_IB_UPSELL_IB_LOGOS2%2C%2C-1%7Cb2bmcpers%2C%2C-1%7CPremMCBtmSess%2C%2C-1%7CMC_IB_UPSELL_IB_LOGOS%2C%2C-1%7CLaFourchette+Banners%2C%2C-1%7Csess_rev%2C%2C-1%7Csessamex%2C%2C-1%7CPremiumRRSess%2C%2C-1%7CTADORSess%2C%2C-1%7CAdsRetPers%2C%2C-1%7CCOVIDMCSess%2C%2C-1%7CTARSWBPers%2C%2C-1%7CListMCSess%2C%2C-1%7CSPMCSess%2C%2C-1%7CTheForkORSess%2C%2C-1%7CTheForkRRSess%2C%2C-1%7Cpers_rev%2C%2C-1%7CSPACMCSess%2C%2C-1%7CRBAPers%2C%2C-1%7CRestAds%2FRSess%2C%2C-1%7CHomeAPers%2C%2C-1%7CPremiumMobPers%2C%2C-1%7CRCSess%2C%2C-1%7CLaFourchette+MC+Banners%2C%2C-1%7CRestAdsCCSess%2C%2C-1%7CRestPremRPers%2C%2C-1%7CRevHubRMPers%2C%2C-1%7CUVOwnersPers%2C%2C-1%7Csh%2C%2C-1%7Cpssamex%2C%2C-1%7CTheForkMCCSess%2C%2C-1%7CCrisisPers%2C%2C-1%7CCYLPers%2C%2C-1%7CCCPers%2C%2C-1%7CRepTarMCPers%2C%2C-1%7Cb2bmcsess%2C%2C-1%7CSPMCPers%2C%2C-1%7CRevHubRMSess%2C%2C-1%7CPremRetSess%2C%2C-1%7CViatorMCSess%2C%2C-1%7CPremiumMCPers%2C%2C-1%7CAdsRetSess%2C%2C-1%7CPremiumRRPers%2C%2C-1%7CCOVIDMCPers%2C%2C-1%7CRestAdsCCPers%2C%2C-1%7CTADORPers%2C%2C-1%7CSPACMCPers%2C%2C-1%7CTheForkORPers%2C%2C-1%7CPremMCBtmPers%2C%2C-1%7CTheForkRRPers%2C%2C-1%7CTARSWBSess%2C%2C-1%7CPremiumORPers%2C%2C-1%7CRestAdsSess%2C%2C-1%7CRBASess%2C%2C-1%7CSPORPers%2C%2C-1%7Cperssticker%2C%2C-1%7CListMCPers%2C%2C-1%7C; __gads=ID=beedc7e8084a16fc:T=1592230814:S=ALNI_MYuW3WhZhoBiZdefd0XMT4blZo0BQ; VRMCID=%1%V1*id.10568*llp.%2FTourism-g187497-Barcelona_Catalonia-Vacations%5C.html*e.1592835771368; ki_r=; ictf_master=vid~24eff988-c632-4e56-86cb-04e146e76abd; ictf_il2627=rlt~1592231284~land~2___; ictf_in2627=rlt~1592231284~land~2___; ki_t=1592231275372%3B1592231275372%3B1592231379842%3B1%3B2; ak_bmsc=1629D7A7F9973CF8EA593697FFE7FBF45F64F9CD662D00005E92E75EE5565763~plfwIE12s7oyOE5Mlw4XrLOP7Okx9YKVUw97+/Av6u7bh+welvaipuU5NOKYMKYlqMZaXZDocRUIBik0pM8rVos5mrx4oAM4zBVowz3zpyKiLeCfPz+6GB6cpmiiRp/dCBdSg4RwBRLiRx7FMRr7GUXBA0GUxoh3m2+GTMd5lQHQJ0Q41EgfTcyY6IXylLkjdqRZCTM/tlyLW8+yRQ6ZBL60q3o7z09fpHpD+cEcaDH0k=; __vt=WkdmHxhmvCP3KQRJABQCq4R_VSrMTACwWFvfTfL3vw4E0aLhU8r3jdO7DKPcwwQKjcbIbc3fuhcmb9k2OldKXQku4_FJblhth2HX7sRodVXT0urAUV9VyLuft4aALBRKcjjEG3Z35kuTOIihLnUs7CSLTw; PAC=APKztscF7wbwyXWF0LnXgINwq3O8yDOnu7OtQ0tvsM9Wbb4mk5YxKTxpiVfxjGn4pEH4d2C0YjK2i_fthtveyERWH8Uj9stmQacnGedQSbmtzxHmKbl1VR5PN5Aad1YI6X1UQEnDmNbbI--9zOZjO0p230qSFfXyTL1xAYfmfB9cAZh24pSBp8NHdrLYAiXtrshA1d7N2WS-oYl1w8Y-HfeaIdFlChTieiFb3u5rLmrVE4IFfwllVxvRWwBK_-oo3A%3D%3D; TAReturnTo=%1%%2FAttraction_Review-g187497-d190166-Reviews-Basilica_of_the_Sagrada_Familia-Barcelona_Catalonia.html; roybatty=TNI1625u0021ANH%2B44x%2FYBczqZ3zBYKcI4jTwPEdpK5Sn7kIJnSeBk6uNxe8FRrSk1DNZ7Lv7F29Rfab%2BEoWtVZ%2Fi%2FUh1fVdOORJMDC3sNIG1Pj4%2B6Z7ogfU%2B3sPdalscK5rkWvktrf5OeRQRkU7%2BKpw5eJs323z4fRt0IaEvqlLXXDZJJnxt9Ad%2C1; SRT=%1%enc%3AXLSRLedTEqLvUPoJNmz67cBGBNkO17wNfer%2Btk%2BoO11wsWIjnLZ8DyAC3xolzDqgyx9HjuV9fk0%3D; TASession=V2ID.D541A285935E7CE3F3C5CA5676153B45*SQ.57*LS.DemandLoadAjax*GR.1*TCPAR.72*TBR.92*EXEX.79*ABTR.32*PHTB.33*FS.51*CPU.82*HS.recommended*ES.popularity*DS.5*SAS.popularity*FPS.oldFirst*LF.en*FA.1*DF.0*IR.4*TRA.false*LD.190166*EAU._; TAUD=LA-1592230813482-1*RDD-1-2020_06_15*LG-8293544-2.1.F.*LD-8293545-.....; bm_sv=5AFFC1D289E2463BB6773C6E9827F1AA~gafAGe6AiFvGDauEVElCtIYle1rnCC2aDqWCEG3bgY88zumi3KeQAe7CiJ+SSTzd84zMit9vo65/UYy6xeVaaxeFzKTy4usITC51FTk0JmNa8y6/08mVV6AWjd3NU9Q6CWYzaBLLDEPJr/BbovaI3K2o7bcQEZf4ZoNR6fAXKMQ=; __vt=MckNRzxvkw2mQbNRABQCq4R_VSrMTACwWFvfTfL3vw4FdTIpMdvQVedYpMYp7wnmpmhFS4YohB53mOPCtj64a-bmu0NcQCVlSK7xtTgkT11ZpnbORYJ6a8VVYYLnENmmvwJDUH6KXFHtGFIlroALXbvwKg'
}

num = 5


def run_query(location_id):
    result = {}
    for i in range(num):
        print(" - "+str(i))
        query = [{
                     "query": """query ReviewListQuery($locationId: Int!, $offset: Int, $limit: Int, $filters: [FilterConditionInput!], $prefs: ReviewListPrefsInput, $initialPrefs: ReviewListPrefsInput, $filterCacheKey: String, $prefsCacheKey: String, $keywordVariant: String!, $needKeywords: Boolean = true) {↵  locations(locationIds: [$locationId]) {↵    locationId↵    name↵    placeType↵    keywords(variant: $keywordVariant) @include(if: $needKeywords) {↵      keywords {↵        keyword↵      }↵    }↵    reviewListPage(page: {offset: $offset, limit: $limit}, filters: $filters, prefs: $prefs, initialPrefs: $initialPrefs, filterCacheKey: $filterCacheKey, prefsCacheKey: $prefsCacheKey) {↵      reviews {↵        ... on Review {↵          id↵          absoluteUrl↵          text↵          publishedDate↵          language↵          rating↵        }↵      }↵    }↵  }↵}""",
                     "variables": {"locationId": location_id, "offset": i * 20,
                                   "filters": [{"axis": "LANGUAGE", "selections": ["en"]}], "initialPrefs": {},
                                   "keywordVariant": "location_keywords_v2_llr_order_30_en", "limit": 20,
                                   "needKeywords": False, "prefs": None, "prefsCacheKey": "locationReviewPrefs"}}]
        request = requests.post('https://www.tripadvisor.com/data/graphql/batched', json=query, headers=headers)
        if request.status_code == 200:
            if "errors" in request.json()[0]:
                print("error: {} - {}".format(location_id, i*20))
                time.sleep(3)
                i -= 1
                continue
            location_id = request.json()[0].get("data").get("locations")[0].get("locationId")
            if location_id not in result:
                result[location_id] = request.json()[0].get("data").get("locations")[0]
            else:
                if "reviews" in request.json()[0].get("data").get("locations")[0].get("reviewListPage"):
                    result[location_id].get("reviewListPage").get("reviews").extend(
                        request.json()[0].get("data").get("locations")[0].get("reviewListPage").get("reviews"))
            time.sleep(1)
        else:
            print(request.text.encode('utf8'))
            raise Exception("Query failed to run by returning code of {}.".format(request.status_code))

    return result


for k in range(21, 500):
    print(k)
    locID = locationIds[k]
    r = []
    for v in run_query(locID).values():
        r.append(v)
    with open('reviews/reviews_{}.json'.format(locID), 'w') as fp:
        json.dump(r, fp)
