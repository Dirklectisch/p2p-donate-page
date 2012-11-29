(ns p2pfoundation.core
  (:require [domina :as dom])
  (:use [domina.xpath :only [xpath]]
        [domina.events :only [listen! target]]
        [domina.css :only [sel]]))

(dom/set-value! (xpath "//input[@name='donationAmount']") "15")
             
(defn hide-element [id]
      (dom/set-styles! (sel (str "#" id))
                       {:visibility "collapse" :display "none"}))
                       
(defn show-element [id]
     (dom/set-styles! (sel (str "#" id))
                      {:visibility "visible" :display "block"}))
             
(defn handle-frequency-change [evt]
      (let [freq (dom/value (:target evt))]
           (if (= freq "oneTime")
               (do (show-element "oneTimeForm")
                   (hide-element "monthlyForm"))
               (do (show-element "monthlyForm")
                   (hide-element "oneTimeForm")))))
            
(listen! (xpath "//input[@name='DonateFrequency']") 
         :change
         handle-frequency-change)

(defn set-new-amount [amount]
      (do (dom/set-value! (xpath "//input[@name='amount']") amount)
          (dom/set-value! (xpath "//input[@name='a3']") amount)
          (dom/set-value! (xpath "//input[@name='donationAmount']") amount)))

(defn handle-button-click [evt]
      (let [btn (:target evt)]
           (set-new-amount (dom/text btn)) )) 

(listen! (xpath "//a[@class='blue_btn']") 
         :click 
         handle-button-click)

(defn handle-amount-change [evt]
      (let [amount (dom/value (:target evt))]
           (set-new-amount amount)))
      
(listen! (xpath "//input[@name='donationAmount']")
         :change
         handle-amount-change)