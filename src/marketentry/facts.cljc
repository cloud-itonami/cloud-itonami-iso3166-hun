(ns marketentry.facts "Hungary market-entry catalog.")
(def catalog
  {"HUN" {:name "Hungary"
          :owner-authority "KH / EKR (Elektronikus Közbeszerzési Rendszer)"
          :legal-basis "Kbt.; EU directives"
          :national-spec "EKR supplier registration + cégjegyzékszám / adószám"
          :provenance "https://ekr.gov.hu/"
          :required-evidence ["adószám/cégjegyzékszám record" "EKR registration record" "cégkivonat extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / KH"
          :rep-legal-basis "EU establishment or Hungarian company registration for many procedures"
          :rep-provenance "https://ekr.gov.hu/"
          :corporate-number-owner-authority "NAV / ORFK company court"
          :corporate-number-legal-basis "adószám / cégjegyzékszám"
          :corporate-number-provenance "https://www.nav.gov.hu/"}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
