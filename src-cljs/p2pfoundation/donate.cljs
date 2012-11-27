(ns p2pfoundation.donate
  (:require [domina :as dom])
  (:use [domina.xpath :only [xpath]]
        [domina.events :only [listen! target]]))

(dom/set-value! (xpath "//input[@name='donationAmount']") "15")

(defn set-new-amount [amount]
      (dom/set-value! (xpath "//input[@name='amount']") amount))

(defn handle-button-click [evt]
      (let [btn (:target evt)]
      (do (dom/set-value! (xpath "//input[@name='donationAmount']") (dom/text btn))
          (set-new-amount (dom/text btn)))))

(listen! (xpath "//a[@class='blue_btn']") 
         :click 
         handle-button-click)

(defn handle-amount-change [evt]
      (let [amount (dom/value (:target evt))]
           (set-new-amount amount)))
      
(listen! (xpath "//input[@name='donationAmount']")
         :change
         handle-amount-change)         

;;(js/alert "Hello from ClojureScript!")