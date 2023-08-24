(ns sst-manager.app
  (:require
   [sst-manager.cards :as cards]
   [sst-manager.icons :as icons]
   [clojure.string :as string]
   [com.fulcrologic.fulcro.algorithms.react-interop :as interop]
   [com.fulcrologic.fulcro.algorithms.merge :as fulcro.merge]
   [com.fulcrologic.fulcro.algorithms.normalized-state :as fulcro.normalized-state]
   [com.fulcrologic.fulcro.algorithms.tx-processing :as fulcro.tx-processing]
   [com.fulcrologic.fulcro.application :as fulcro.app]
   [com.fulcrologic.fulcro.components :as fulcro.comp :refer [defsc get-query transact!]]
   [com.fulcrologic.fulcro.dom :as fulcro.dom]
   [com.fulcrologic.fulcro.mutations :refer [defmutation set-value! toggle!]]
   [com.fulcrologic.fulcro.react.hooks :refer [use-state]]
   [com.fulcrologic.fulcro.react.version18 :refer [with-react18]]
   [goog.uri.utils :as guri]
   [oops.core :refer [ocall! oget]]
   ["@mui/icons-material/CheckCircleRounded" :default CheckCircleRounded]
   ["@mui/icons-material/ContentCopy" :default ContentCopy]
   ["@mui/icons-material/Delete" :default Delete]
   ["@mui/icons-material/Done" :default Done]
   ["@mui/icons-material/Edit" :default Edit]
   ["@mui/icons-material/Explore" :default Explore]
   ["@mui/icons-material/Gavel" :default Gavel]
   ["@mui/icons-material/Save" :default Save]
   ["@mui/joy/Avatar" :default Avatar]
   ["@mui/joy/Box" :default Box]
   ["@mui/joy/Button" :default Button]
   ["@mui/joy/CssBaseline" :default CssBaseline]
   ["@mui/joy/Divider" :default Divider]
   ["@mui/joy/FormLabel" :default FormLabel]
   ["@mui/joy/IconButton" :default IconButton]
   ["@mui/joy/Input" :default Input]
   ["@mui/joy/Modal" :default Modal]
   ["@mui/joy/ModalClose" :default ModalClose]
   ["@mui/joy/ModalDialog" :default ModalDialog]
   ["@mui/joy/ModalOverflow" :default ModalOverflow]
   ["@mui/joy/Radio" :default Radio :refer [radioClasses]]
   ["@mui/joy/RadioGroup" :default RadioGroup]
   ["@mui/joy/Stack" :default Stack]
   ["@mui/joy/Sheet" :default Sheet]
   ["@mui/joy/Switch" :default Switch]
   ["@mui/joy/ToggleButtonGroup" :default ToggleButtonGroup]
   ["@mui/joy/Typography" :default Typography]
   ["@mui/joy/styles" :refer [CssVarsProvider]]
   ))

(def check-circle-rounded-icon (interop/react-factory CheckCircleRounded))
(def content-copy-icon (interop/react-factory ContentCopy))
(def delete-icon (interop/react-factory Delete))
(def done-icon (interop/react-factory Done))
(def edit-icon (interop/react-factory Edit))
(def explore-icon (interop/react-factory Explore))
(def gavel-icon (interop/react-factory Gavel))
(def save-icon (interop/react-factory Save))

(def avatar (interop/react-factory Avatar))
(def box (interop/react-factory Box))
(def button (interop/react-factory Button))
(def css-baseline (interop/react-factory CssBaseline))
(def css-vars-provider (interop/react-factory CssVarsProvider))
(def divider (interop/react-factory Divider))
(def form-label (interop/react-factory FormLabel))
(def icon-button (interop/react-factory IconButton))
(def input (interop/react-factory Input))
(def modal (interop/react-factory Modal))
(def modal-close (interop/react-factory ModalClose))
(def modal-dialog (interop/react-factory ModalDialog))
(def modal-overflow (interop/react-factory ModalOverflow))
(def radio (interop/react-factory Radio))
(def radio-group (interop/react-factory RadioGroup))
(def stack (interop/react-factory Stack))
(def sheet (interop/react-factory Sheet))
(def switch (interop/react-factory Switch))
(def toggle-button-group (interop/react-factory ToggleButtonGroup))
(def typography (interop/react-factory Typography))

(defonce app (with-react18 (fulcro.app/fulcro-app)))

(defmutation select-faction [{:keys [selected-faction]}]
  (action [{:keys [state]}]
          (swap! state #(assoc % :selected-faction selected-faction))))

; TODO Things get funky when faction is still null
(defsc FactionSelector [this {:keys [selected-faction]}]
  {:ident (fn [] [:component/id ::FactionSelector])
   :query [[:selected-faction '_]]
   :initial-state {:selected-faction {}}}
  ; TODO Move label above, like in cards. Change color, check typefaces
  ; TODO Make icons with transparent background, or check if the avatar component does that
  (radio-group {:overlay true
                :sx (clj->js {
                              :flexDirection :row
                              :gap 2
                              (str "& ." (oget radioClasses "checked"))
                              {(str "& ." (oget radioClasses "action"))
                               {:inset -1,
                                :border "3px solid"
                                :borderColor "primary.500"
                                }}
                              (str "& ." (oget radioClasses "radio"))
                              { :display :contents
                               "& > svg" {
                                          :zIndex 2
                                          :position "absolute"
                                          :top "-8px"
                                          :right "-8px"
                                          :bgcolor "background.surface"
                                          :borderRadius "50%"
                                          }
                               }
                              })
                :value selected-faction
                :onChange #(transact! app [(select-faction {:selected-faction (keyword (oget % "target.value"))})])}
               (->> [[:forge "Forge" icons/forge]
                     [:komplex "Komplex" icons/komplex]]
                    (mapv (fn [[faction faction-text faction-icon-url]]
                            (sheet {:key faction
                                    :sx {:p 2
                                         :display :flex
                                         :flexDirection :column
                                         :alignItems :center}}
                                   (radio {:value faction
                                           :checkedIcon (check-circle-rounded-icon)})
                                   (avatar {:src faction-icon-url})
                                   (form-label {:htmlFor faction} faction-text)))))
               )
  )

(def ui-faction-selector (fulcro.comp/factory FactionSelector))

(defmutation toggle-free-build [_params]
  (action [{state :state}]
          (swap! state #(assoc % :free-build? (not (:free-build? %))))))

(defsc FreeBuildSelector [this {:keys [free-build?]}]
  {:ident (fn [] [:component/id ::FreeBuildSelector])
   :query [[:free-build? '_]]
   :initial-state {:free-build? {}}}
  (switch {:startDecorator (box {:sx {:display :flex}} (typography {} "Follow rules") (gavel-icon))
           :endDecorator (box {:sx {:display :flex}} (explore-icon) (typography {} "Free build"))
           :checked free-build?
           :onChange #(transact! app [(toggle-free-build)])})
  )
(def ui-free-build-selector (fulcro.comp/factory FreeBuildSelector))

(defsc OurModal [this {:keys [open?]} {:keys [callback]}]
  (modal
   {:open open?
    :onClose callback}
   (modal-overflow
    {}
    (modal-dialog
     {}
     (modal-close {:sx {:top "calc(-1/4 * var(--IconButton-size))"
                        :right "calc(-1/4 * var(--IconButton-size))"
                        :boxShadow "0 2px 12px 0 rgba(0 0 0 / 0.2)"
                        :borderRadius "50%"
                        :bgcolor "background.surface"}})
     (fulcro.comp/children this)))))
(def ui-our-modal (fulcro.comp/factory OurModal))

;; Design notes
;; Not every visible chassis card is a chassis in play, for example in the selector.
;; And not every chassis in the squad has a visible card, for example when rendering as text.
;; Therefore it makes sense to separate the image rendering from the gameplay stats.
(defsc ChassisCard [this props]
  {:use-hooks? true}
  ; TODO Maybe figure some other name than "type", so could destructure without shadowing core functions
  (let [[flipped? setFlipped] (use-state false)
        url-base (str "https://cards.snapshipstactics.com/card-images/chassis-"
                      (name (:faction props))
                      "-"
                      (string/lower-case (:ship props))
                      "-"
                      (string/replace (string/lower-case (:type props)) #" " "-"))]
    (box
     {:onClick #(setFlipped (not flipped?))
      :sx (merge {:transition "transform 0.8s"
                  :transformStyle "preserve-3d"
                  :width "315px" :height "315px"}
                 (when flipped? {:transform "rotateY(180deg)"}))}
     (fulcro.dom/img {:style {:position :absolute :backfaceVisibility :hidden} :src (str url-base "-front.png")})
     (fulcro.dom/img {:style {:position :absolute :transform "rotateY(180deg)" :backfaceVisibility :hidden} :src (str url-base "-back.png")}))))
(def ui-chassis-card (fulcro.comp/factory ChassisCard {:keyfn :type}))

(defsc PartCard [this props]
  {}
  ; TODO Maybe figure some other name than "type", so could destructure without shadowing core functions
  (fulcro.dom/img {:src (str "https://cards.snapshipstactics.com/card-images/"
                             (name (:slot props))
                             "-"
                             (name (:faction props))
                             "-"
                             (string/replace (string/lower-case (:name props)) #" " "-")
                             (if (:disabled? props) "-back.png" "-front.png"))}))
(def ui-part-card (fulcro.comp/factory PartCard {:keyfn #(str (:faction %) (:name %))}))

(defn delete-me-button
  ([this]
   (delete-me-button this nil))
  ([this cascade]
   (icon-button {:onClick #(transact! this `[(sst-manager.app/delete-me {:cascade ~cascade})])
                 :variant :solid
                 :color :danger
                 :sx {:position "absolute"
                      :top "-8px"
                      :left "-8px"
                      }}
                (delete-icon))))

(defsc Part [this {id :part/id
                   :keys [part]}]
  {:ident :part/id
   :query [:part/id :part]}
  (sheet {:sx {:width :fitContent}}
         (ui-part-card part)
         (delete-me-button this)))

(def ui-part (fulcro.comp/factory Part {:keyfn :part/id}))

(defmutation open-part-selector [_params]
  (action [{:keys [state ref]}]
          (swap! state #(assoc-in % [:component/id ::PartSelector :open-ship] ref))))

(defmutation close-part-selector [_params]
  (action [{:keys [state]}]
          (swap! state #(assoc-in % [:component/id ::PartSelector :open-ship] nil))))

(defn positions
  [pred coll]
  (keep-indexed (fn [idx x]
                  (when (pred x)
                    idx))
                coll))

(defn ship->code [ship]
  (let [chassis-code (str "c" (first (positions #{(:chassis ship)} cards/chassis)))
        name-code (if (empty? (:name ship))
                    nil
                    (str "n" (-> ship
                                 :name
                                 (string/replace #"-" "\\-")
                                 (string/replace #"," "\\,"))))
        part-codes (map #(str "p" (first (positions #{(:part %)} cards/parts))) (:parts ship))]
    (string/join "-"
                 (remove nil? (apply conj [chassis-code name-code] part-codes)))))

(defn ships->codes [ships]
  (map ship->code ships))

(defn code->ship [code]
  (if (= \c (nth code 0))
	; new style code: c1-nMyname-p1-p2-p3
	(reduce
	 (fn [ship code-part]
	   (let [rest-part (subs code-part 1)]
		 (case (nth code-part 0)
		   \c (assoc ship :chassis (nth cards/chassis (js/parseInt rest-part)))
           \n (assoc ship :name (-> rest-part
                                    (string/replace #"\\-" "-")
                                    (string/replace #"\\," ",")))
           \p (update ship :parts #(conj (or % []) {:part (nth cards/parts (js/parseInt rest-part))})))))
	 {}
	 (string/split code #"(?<!\\)-"))
	; old style code: 1-2-3-4
	(let [[ship-code & part-codes] (map js/parseInt (string/split code #"-"))]
	  {:chassis (nth cards/chassis ship-code)
	   :parts (map (fn [part-index] {:part (nth cards/parts part-index)}) part-codes)})))

(comment
  (code->ship "c0-nFoo-p2-p3-p4"))

(comment
  (code->ship "1-2-3"))

(defn codes->url-params [codes]
  (if (empty? codes)
    ""
    (string/join "," codes)))

(defn add-ship! [ship]
  (transact! app [`(sst-manager.app/add-ship ~(dissoc ship :parts))])
  (doseq [part (:parts ship)]
    (transact! app [`(sst-manager.app/add-part ~part)])))

(defn duplicate-me-button [ship]
  (icon-button {:onClick #(add-ship! ship)
                :variant :solid
                :sx {:position "absolute"
                     :top "-8px"
                     :left "32px"}}
                (content-copy-icon)))

(declare PresetLoader)

; Local storage has no events to listen for changes,
; so a separate message channel needs to be managed to see changes done in other tabs
(def broadcast-channel (js/BroadcastChannel. "presets"))

(defn persist-presets! [presets]
  (-> (ocall! js/navigator "storage.persist")
      (.then #(js/console.log "Persistent storage" (if % "granted" "denied"))))
  (ocall! js/window "localStorage.setItem" "presets" (string/join "," presets))
  (ocall! broadcast-channel "postMessage" "new presets")
  presets)

(defn load-presets-from-storage! []
  (fulcro.merge/merge! app
                       {:saved-presets (into (sorted-set)
                                             (-> (ocall! js/window "localStorage.getItem" "presets")
                                                 (str ",")
                                                 (clojure.string/split #"(?<!\\),")))}
                       [[:saved-presets '_]]))

(ocall! broadcast-channel "addEventListener" "message" load-presets-from-storage!)

(defmutation save-preset [{:keys [ship-code]}]
  (action [{:keys [state]}]
          (swap! state #(update % :saved-presets
                                (fn [presets] (persist-presets! (conj (or presets (sorted-set)) ship-code)))))))

(defmutation delete-preset [{:keys [ship-code]}]
  (action [{:keys [state]}]
          (swap! state #(update % :saved-presets
                                (fn [presets] (persist-presets! (disj presets ship-code)))))))

(defn save-ship-button [ship]
  (icon-button {:onClick #(transact! app [(save-preset {:ship-code (ship->code ship)})])
                :variant :solid
                :sx {:position "absolute"
                     :top "-8px"
                     :left "72px"}}
                (save-icon)))


(declare Ship)

(defn denormalize-ship [state ship]
  (com.fulcrologic.fulcro.algorithms.denormalize/db->tree (get-query Ship)
                                                          ship
                                                          state))

(defn denormalize-ships [state]
  (map #(denormalize-ship state %) (:ships state)))

(defn get-current-ships []
  (denormalize-ships @(::fulcro.app/state-atom app)))

(defn get-current-url []
  (.. js/document -location -search))

(defn update-ship-url-params! [state]
  (.. js/window -history (replaceState nil ""
                                       (->> state
                                            (denormalize-ships)
                                            (ships->codes)
                                            (codes->url-params)
                                            (guri/setParam (get-current-url) "ships"))))
  state)

(defmutation change-ship-name [{:keys [new-name]}]
  (action [{:keys [state ref]}]
          (swap! state #(update-ship-url-params!
                         (assoc-in % (conj ref :name) new-name)))))

;; Design notes
;; The properties of the ship, like evasion, hull, missiles, available power etc need to be on the abstract ship,
;; not the chassis card, since there are multiple ways to render these: text, cubes on cards, cubes next to cards etc.
(defsc Ship [this {id :ship/id
                   ship-name :name ; To avoid masking a core function
                   :keys [chassis parts evasion hull missiles]
                   :as props}]
  {:ident :ship/id
   :query [:ship/id :name :chassis :evasion :hull :missiles
           {:parts (get-query Part)}]
   :use-hooks? true}
  (sheet
   {:sx {:marginTop 1}}
   (let [[editing? set-editing] (use-state false)
         default-name (str (:ship chassis) " " (:type chassis))]
     (stack {:direction :row}
            (if editing?
              (fulcro.dom/form
               {:onSubmit (fn [event]
                            (.preventDefault event)
                            (transact! this [(change-ship-name {:new-name (oget event "target.0.value")})])
                            (set-editing false))}
               (input {:placeholder default-name
                       :defaultValue ship-name
                       :autoFocus true
                       :sx {"--Input-decoratorChildHeight" "40px"}
                       :endDecorator (icon-button {:type :submit
                                                   :variant :solid
                                                   :color :primary
                                                   :sx {:borderTopLeftRadius 0
                                                        :borderBottomLeftRadius 0}}
                                                  (done-icon))
                       }))
              (typography {:level :title-lg} (if (empty? ship-name) default-name ship-name)))
            (if (not editing?)
              (icon-button {:size :sm
                            :onClick #(set-editing (not editing?))}
                           (edit-icon)))))
   (typography {:level :body-md} "(Build code " (ship->code props) ")")

   (stack {:direction :row
           :sx {:position :relative
                :marginTop 1
                :flexWrap :wrap}}
          (ui-chassis-card chassis)
          (delete-me-button this #{:parts})
          (duplicate-me-button props)
          (save-ship-button props)
          ; TODO
          ; ☐ Global selector for strict choices or free form
          ; ☐ Generate a list of part ui components that are either empty based on chassis or populated from selections
          ; ☐ Condensed view, text only, no cards
          (mapv ui-part parts)
          (button {:variant :outlined
                   :onClick #(transact! this [(open-part-selector)])}
                  "Add part"
                  ))))
(def ui-ship (fulcro.comp/factory Ship {:keyfn :ship/id}))

(defmutation delete-me [{:keys [cascade]}]
  (action [{:keys [state ref]}]
          (swap! state #(update-ship-url-params! (fulcro.normalized-state/remove-entity % ref cascade)))))

(defmutation add-part [{:keys [ship part]}]
  (action
   [{:keys [state]}]
   (swap! state
          (fn [state]
            (let [max-id (->> state
                              :part/id
                              keys
                              (reduce max 0) ; (apply max) would explode with an empty list
                              )
                  ship (or ship
                           [:ship/id
                            (->> state
                                 :ship/id
                                 keys
                                 (reduce max 0) ; (apply max) would explode with an empty list
                                 )])]
              (update-ship-url-params!
               (fulcro.merge/merge-component state Part {:part/id (inc max-id) :part part}
                                             :append (conj ship :parts))))))))

(defsc PartSelector [this {:keys [selected-faction open-ship selected-slot]}]
  {:ident (fn [] [:component/id ::PartSelector])
   :query [[:selected-faction '_]
           :selected-slot
           {:open-ship (get-query Ship)}]
   :initial-state {:open-ship {}
                   :selected-slot nil}}
  (ui-our-modal (fulcro.comp/computed {:open? (boolean open-ship)} ; Joy modal doesn't like nulls
                                      {:callback #(transact! this [(close-part-selector)])})
                (stack
                 {:sx {:alignItems :center}}
                 (toggle-button-group {:variant :outlined
                                       :value selected-slot
                                       :onChange (fn [event new-value] (set-value! this :selected-slot new-value))}
                                      (->> ["cockpit" "thruster" "wing" "systems"]
                                           (mapv #(button {:key %
                                                           :value %}
                                                          (string/capitalize (name %))))))
                 (stack
                  {:direction :row
                   :sx {:flexWrap :wrap
                        :justifyContent :center}}
                  (->> cards/parts
                       (filter #(and (= selected-faction (:faction %))
                                     (or (not selected-slot)
                                         (#{(keyword selected-slot)} (:slot %)))))
                       (mapv #(sheet {:key (str (:faction %) (:name %))
                                      :sx {:m 1}
                                      :onClick (fn []
                                                 (transact! app [(add-part {:part % :ship (fulcro.comp/get-ident Ship open-ship)})
                                                                 (close-part-selector)]))}
                                     (ui-part-card %))))))))

(def ui-part-selector (fulcro.comp/factory PartSelector))

(defmutation add-ship [ship]
  (action [{:keys [state]}]
          (let [max-id (->> @state
                           :ship/id
                           keys
                           (reduce max 0) ; (apply max) would explode with an empty list
                           )]
            (swap! state (fn [state]
                           (update-ship-url-params!
                            (fulcro.merge/merge-component state Ship (assoc ship :ship/id (inc max-id))
                                                          :append [:ships])))))))

(defmutation open-chassis-selector [_params]
  (action [{:keys [state]}]
          (swap! state #(assoc % :chassis-selector-open true))))

(defmutation close-chassis-selector [_params]
  (action [{:keys [state]}]
          (swap! state #(assoc % :chassis-selector-open false))))

(defsc ChassisSelector [this {:keys [selected-faction chassis-selector-open]}]
  {:ident (fn [] [:component/id ::ChassisSelector])
   :query [[:selected-faction '_]
           [:chassis-selector-open '_]]
   :initial-state {:selected-faction {}
                   :chassis-selector-open false}}
  (ui-our-modal (fulcro.comp/computed {:open? (boolean chassis-selector-open)} ; Joy modal doesn't like nulls
                                      {:callback #(transact! this [(close-chassis-selector)])}
                 )
                (stack
                 {:direction :row
                  :sx {:flexWrap :wrap
                       :justifyContent :center}}
                 (->> cards/chassis
                      (filter #(= selected-faction (:faction %)))
                      (mapv #(sheet {:key (:type %)
                                     :sx {:m 1}
                                     :onClick (fn []
                                                (transact! app [(add-ship {:chassis %})
                                                                (close-chassis-selector)]))}
                                    (ui-chassis-card %)))))))

(def ui-chassis-selector (fulcro.comp/factory ChassisSelector))

(defmutation open-ship-import [_params]
  (action [{:keys [state]}]
          (swap! state #(assoc % :ship-import-open true))))

(defmutation close-ship-import [_params]
  (action [{:keys [state]}]
          (swap! state #(assoc % :ship-import-open false))))

(defsc ShipImport [this {:keys [ship-import-open]}]
  {:ident (fn [] [:component/id ::ShipImport])
   :query [[:ship-import-open '_]]
   :initial-state {:ship-import-open false}}
  (let [close-callback #(transact! this [(close-ship-import)])]
    (ui-our-modal (fulcro.comp/computed {:open? (boolean ship-import-open)} ; Joy modal doesn't like nulls
                                        {:callback close-callback})
                  (fulcro.dom/form {:onSubmit (fn [event]
                                                (.preventDefault event)
                                                (add-ship! (code->ship (oget event "target.0.value")))
                                                (close-callback))}
                                   (input {:autoFocus true
                                           :required true
                                           :placeholder "Input ship build code"})
                                   (button {:type :submit} "Add"))
                  )))

(def ui-ship-import (fulcro.comp/factory ShipImport))

(def official-presets
  ["c0-p5-p17-p16-p30-p27-p23-p12-p10"
   "c1-p5-p17-p16-p30-p27-p22-p9-p12"
   "c2-p5-p17-p16-p30-p27-p10-p21-p8"
   "c3-p4-p15-p29-p25-p1"
   "c4-p4-p18-p29-p25-p1"
   "c5-p6-p19-p31-p7-p26-p11"
   "c6-p6-p19-p31-p24-p26-p11"
   "c7-p6-p19-p31-p7-p26-p3"
   "c8-p6-p20-p32-p0-p26-p28"
   "c9-p35-p50-p68-p60-p41"
   "c10-p35-p47-p68-p45-p60"
   "c11-p36-p48-p67-p39-p66-p61"
   "c12-p36-p48-p67-p39-p61-p34"
   "c13-p36-p49-p67-p33-p66-p61"
   "c14-p36-p49-p71-p38-p66-p46"
   "c15-p37-p52-p69-p70-p43-p63-p64-p62"
   "c16-p37-p53-p69-p70-p58-p63-p55-p64"
   "c17-p37-p52-p69-p70-p44-p64-p65-p54"
   ])

(defmutation open-preset-loader [_params]
  (action [{:keys [state]}]
          (swap! state #(assoc % :preset-loader-open true))))

(defmutation close-preset-loader [_params]
  (action [{:keys [state]}]
          (swap! state #(assoc % :preset-loader-open false))))

(defsc PresetLoader [this {:keys [selected-faction preset-loader-open saved-presets]}]
  {:ident (fn [] [:component/id ::ShipImport])
   :query [[:selected-faction '_]
           [:preset-loader-open '_]
           [:saved-presets '_]]
   :initial-state {:preset-loader-open false
                   :saved-presets []}}
  (let [close-callback #(transact! this [(close-preset-loader)])]
    (fulcro.comp/fragment
     {}
     (ui-our-modal
      (fulcro.comp/computed {:open? (boolean preset-loader-open)} ; Joy modal doesn't like nulls
                            {:callback close-callback})
      (typography {:level :title-lg} "Official presets")
	  (stack {:spacing 1
			  :m 1}
			 (let [presets (->> official-presets
								(map (fn [ship-code] {:ship-code ship-code :ship (code->ship ship-code)}))
								(filter #(= selected-faction (:faction (:chassis (:ship %))))))]
			   (mapv (fn [{:keys [ship ship-code]}]
					   (stack {:key ship-code
							   :direction :row
							   :spacing 1
							   :alignItems :center}
							  (button {:onClick (fn [event]
												  (add-ship! (code->ship ship-code))
												  (close-callback))} "Add")
							  (typography {:level :body-md} (:ship (:chassis ship)) " " (:type (:chassis ship)) " (build code " ship-code ")")))
					 presets)))
      (divider)
      (typography {:level :title-lg} "Saved presets")
      (stack {:spacing 1
              :m 1}
             (let [presets (->> saved-presets
                                (map (fn [ship-code] {:ship-code ship-code :ship (code->ship ship-code)}))
                                (filter #(= selected-faction (:faction (:chassis (:ship %))))))]
               (if (empty? presets)
                 (typography {:level :body-md} "No saved presets. Create a ship and use " (save-icon) " to save one")
                 (mapv (fn [{:keys [ship ship-code]}]
                         (stack {:key ship-code
                                 :direction :row
                                 :spacing 1
                                 :alignItems :center}
                                (button {:onClick (fn [event]
                                                    (add-ship! ship)
                                                    (close-callback))}
                                        "Add")
                                (button {:color :danger
                                         :onClick (fn [event]
                                                    (transact! app [(delete-preset {:ship-code ship-code})]))}
                                        "Delete")
                                (typography {:level :body-md}
                                            (or (:name ship)
                                                (str (:ship (:chassis ship)) " " (:type (:chassis ship))))
                                            " (build code " ship-code ")")))
                       presets)))))
     (button {:key "preset-button"
              :variant :outlined
              :onClick #(transact! this [(open-preset-loader)])}
             "Add preset ship"))))

(def ui-preset-loader (fulcro.comp/factory PresetLoader))

(defsc Root [this {:keys [selected-faction
                          faction-selector-data
                          free-build-selector-data
                          chassis-selector-data
                          part-selector-data
                          ship-import-data
                          preset-loader-data
                          ships]}]
  {:query [[:selected-faction '_]
           {:faction-selector-data (get-query FactionSelector)}
           {:free-build-selector-data (get-query FreeBuildSelector)}
           {:chassis-selector-data (get-query ChassisSelector)}
           {:part-selector-data (get-query PartSelector)}
           {:ship-import-data (get-query ShipImport)}
           {:preset-loader-data (get-query PresetLoader)}
           {:ships (get-query Ship)}]
   :initial-state {:faction-selector-data {}
                   :chassis-selector-data {}
                   :part-selector-data {}
                   :free-build-selector-data {}
                   :ship-import-data {}
                   :preset-loader-data {}
                   }}
  (css-vars-provider
   {}
   (css-baseline)
   (box
    {:sx {:margin 2}}
    (ui-faction-selector faction-selector-data)
    #_(ui-free-build-selector free-build-selector-data)
    (ui-chassis-selector chassis-selector-data)
    (ui-part-selector part-selector-data)
    (ui-ship-import ship-import-data)
    (typography {:level :body-lg} "Squad total points: " (->> ships
                                                             (map #(get-in % [:chassis :points]))
                                                             (reduce +)))
    (mapv ui-ship ships)
    (button {:variant :outlined
             :onClick #(transact! this [(open-chassis-selector)])}
            "Add ship")
    (button {:variant :outlined
             :onClick #(transact! this [(open-ship-import)])}
            "Import ship")
    (ui-preset-loader preset-loader-data)
    ))
  )

(defn url->ships [url]
  (map code->ship (-> url
                      (guri/getParamValue "ships")
                      ; If split cannot find any commas, it will return original string, not empty vector.
                      ; Then code->ship will choke on the empty string
                      (str ",")
                      (string/split #"(?<!\\),"))))

(defn init-ships-from-current-url! []
  (let [ships (url->ships (get-current-url))]
    (when (not (empty? ships))
      (transact! app [(select-faction {:selected-faction (-> ships first :chassis :faction)})]))
    (run! add-ship! ships)))

(defn ^:export init
  "Shadow-cljs sets this up to be our entry-point function. See shadow-cljs.edn `:init-fn` in the modules of the main build."
  []
  (load-presets-from-storage!)
  (init-ships-from-current-url!)
  (fulcro.tx-processing/activate-submissions! app) ; Forces transactions created in initial loads to be handled before first render, avoiding flash of uninitialized ui
  (fulcro.app/mount! app Root "app")
  (js/console.log "Loaded"))

(comment
  )

(defn ^:export refresh
  "During development, shadow-cljs will call this on every hot reload of source. See shadow-cljs.edn"
  []
  ;; re-mounting will cause forced UI refresh, update internals, etc.
  (fulcro.app/mount! app Root "app")
  ;; As of Fulcro 3.3.0, this addition will help with stale queries when using dynamic routing:
  (fulcro.comp/refresh-dynamic-queries! app)
  (js/console.log "Hot reload"))

; TODO Sharing by URL
;      - output link
;      - qrcode for easy transfer to mobile
;      - Also some way to add to current squad, not just replace
;      - Is it possible to make social shares render good thumbnails of actual builds?
; TODO Play mode
;      - moving power and heat cubes
;      - activating parts, automatically putting power and heat
;      - attack roller
;      - structure counters
;      - evasion counters
;      - missile counters
;      - save state in URL or some other durable state to avoid losing all when tab is pushed out of ram? Needs to work with multiple tabs running different games
; TODO Streamer mode

; Changelog
; =========
; 2023-08-23
; ----------
; Squad total points
;
; 2023-08-22
; ----------
; Ship names and saving/loading presets
;
; 2023-08-20
; ----------
; Importing single ships with code
; Duplicating ships
; Filters to part selections
; Works a bit better on narrower screens
;
; 2023-08-19
; ----------
; URL now updates with the build, and can be used to share builds.
;
; 2023-08-18
; ----------
; Adding and removing ships and parts
