(ns culture.facts
  "Country-level regional-culture catalog for Hungary (HUN) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"HUN"
   [{:culture/id "hun.dish.goulash"
     :culture/name "Goulash"
     :culture/country "HUN"
     :culture/kind :dish
     :culture/summary "Meal of meat and vegetables seasoned with paprika and other spices, originating in Hungary and a national symbol of the country."
     :culture/url "https://en.wikipedia.org/wiki/Goulash"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hun.dish.langos"
     :culture/name "Lángos"
     :culture/country "HUN"
     :culture/kind :dish
     :culture/summary "Typical Hungarian deep-fried flatbread made from water or milk, flour, yeast and salt."
     :culture/url "https://en.wikipedia.org/wiki/L%C3%A1ngos"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hun.dish.chicken-paprikash"
     :culture/name "Chicken paprikash"
     :culture/country "HUN"
     :culture/kind :dish
     :culture/summary "Popular Hungarian chicken stew simmered in a sauce built on a paprika-infused roux."
     :culture/url "https://en.wikipedia.org/wiki/Chicken_paprikash"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hun.dish.dobos-torte"
     :culture/name "Dobos torte"
     :culture/country "HUN"
     :culture/kind :dish
     :culture/summary "Hungarian sponge cake layered with chocolate buttercream and topped with caramel, created by Budapest chef József C. Dobos in the late 1800s."
     :culture/url "https://en.wikipedia.org/wiki/Dobos_torte"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hun.beverage.tokaji"
     :culture/name "Tokaji"
     :culture/country "HUN"
     :culture/kind :beverage
     :culture/summary "Rich, sweet wine from the Tokaj wine region spanning Hungary and Slovakia, noted for wines made from grapes affected by noble rot."
     :culture/url "https://en.wikipedia.org/wiki/Tokaji"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hun.beverage.palinka"
     :culture/name "Pálinka"
     :culture/country "HUN"
     :culture/kind :beverage
     :culture/summary "Traditional fruit brandy originating in medieval Hungary, registered as a European Union geographical indication since 2004."
     :culture/url "https://en.wikipedia.org/wiki/P%C3%A1linka"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hun.product.winter-salami"
     :culture/name "Winter salami"
     :culture/name-local "Téliszalámi"
     :culture/country "HUN"
     :culture/kind :product
     :culture/summary "Hungarian salami of pork and spices cured in cold air and slowly smoked; Szeged winter salami holds PDO status (2007) and Budapest winter salami PGI status (2009)."
     :culture/url "https://en.wikipedia.org/wiki/Winter_salami"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hun.craft.herend-porcelain"
     :culture/name "Herend Porcelain"
     :culture/country "HUN"
     :culture/kind :craft
     :culture/summary "Luxury hand-painted and gilded porcelain from the manufactory founded in 1826 in Herend, Hungary, historically a purveyor to European royalty."
     :culture/url "https://en.wikipedia.org/wiki/Herend_Porcelain_Manufactory"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hun.festival.busojaras"
     :culture/name "Busójárás"
     :culture/country "HUN"
     :culture/kind :festival
     :culture/summary "Annual end-of-Carnival celebration of the Šokci in Mohács, Hungary, with masked Busós, folk music, parades and dancing; inscribed on UNESCO's intangible cultural heritage list in 2009."
     :culture/url "https://en.wikipedia.org/wiki/Bus%C3%B3j%C3%A1r%C3%A1s"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hun.heritage.hortobagy"
     :culture/name "Hortobágy National Park"
     :culture/country "HUN"
     :culture/kind :heritage
     :culture/summary "800 km² steppe in eastern Hungary, the largest semi-natural grassland in Europe with millennia of pastoral tradition; a UNESCO World Heritage Site inscribed in 1999."
     :culture/url "https://en.wikipedia.org/wiki/Hortob%C3%A1gy_National_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-hun culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "HUN"))
                 " HUN entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
