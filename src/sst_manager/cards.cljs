(ns sst-manager.cards)

(def chassis
  [
   {:faction :forge
    :ship "Falx"
    :type "SC-41 escort"
    :points 5
    :slots [:cockpit :thruster :thruster :wing :systems :systems :systems :systems]
    }
   {:faction :forge
    :ship "Falx"
    :type "GA-15 Ground Assault"
    :points 5
    :slots [:cockpit :thruster :thruster :wing :systems :systems :systems :systems]
    }
   {:faction :forge
    :ship "Falx"
    :type "XF-8 Experimental Fighter"
    :points 5
    :slots [:cockpit :thruster :thruster :wing :systems :systems :systems :systems]
    }
   {:faction :forge
    :ship "Lance"
    :type "SV-51 Scout"
    :points 3
    :slots [:cockpit :thruster :wing :systems :systems]
    }
   {:faction :forge
    :ship "Lance"
    :type "SAC-30 Attack Craft"
    :points 3
    :slots [:cockpit :thruster :wing :systems :systems]
    }
   {:faction :forge
    :ship "Sabre"
    :type "XF-23 Fighter"
    :points 4
    :slots [:cockpit :thruster :wing :systems :systems :systems]
    }
   {:faction :forge
    :ship "Sabre"
    :type "XB-04 Light Bomber"
    :points 4
    :slots [:cockpit :thruster :wing :systems :systems :systems]
    }
   {:faction :forge
    :ship "Sabre"
    :type "XR-01 Fast Recon"
    :points 4
    :slots [:cockpit :thruster :wing :systems :systems :systems]
    }
   {:faction :forge
    :ship "Sabre"
    :type "XG-07 Gunship"
    :points 4
    :slots [:cockpit :thruster :wing :systems :systems :systems]
    }
   {:faction :komplex
    :ship "Locust"
    :type "KLAW Stealth Craft"
    :points 3
    :slots [:cockpit :thruster :wing :systems :systems]
    }
   {:faction :komplex
    :ship "Locust"
    :type "KLAW Close Support Fighter"
    :points 3
    :slots [:cockpit :thruster :wing :systems :systems]
    }
   {:faction :komplex
    :ship "Scarab"
    :type "KLAW Interceptor"
    :points 4
    :slots [:cockpit :thruster :wing :systems :systems :systems]
    }
   {:faction :komplex
    :ship "Scarab"
    :type "Klaw Light Fighter"
    :points 4
    :slots [:cockpit :thruster :wing :systems :systems :systems]
    }
   {:faction :komplex
    :ship "Scarab"
    :type "Klaw Attack Striker"
    :points 4
    :slots [:cockpit :thruster :wing :systems :systems :systems]
    }
   {:faction :komplex
    :ship "Scarab"
    :type "Klaw Jammer"
    :points 4
    :slots [:cockpit :thruster :wing :systems :systems :systems]
    }
   {:faction :komplex
    :ship "Wasp"
    :type "KLAW Heavy Fighter"
    :points 5
    :slots [:cockpit :thruster :wing :wing :systems :systems :systems :systems]
    }
   {:faction :komplex
    :ship "Wasp"
    :type "KLAW Fighter Bomber"
    :points 5
    :slots [:cockpit :thruster :wing :wing :systems :systems :systems :systems]
    }
   {:faction :komplex
    :ship "Wasp"
    :type "KLAW Deep Recon"
    :points 5
    :slots [:cockpit :thruster :wing :wing :systems :systems :systems :systems]
    }
   ]
  )

(def parts
  [
   {:slot :systems
    :faction :forge
    :name "bf-bulkhead"}
   {:slot :systems
    :faction :forge
    :name "lance-booster"}
   {:slot :systems
    :faction :forge
    :name "falx-booster"}
   {:slot :systems
    :faction :forge
    :name "xre-extender"}
   {:slot :cockpit
    :faction :forge
    :name "sc-19-cockpit"}
   {:slot :cockpit
    :faction :forge
    :name "falx-cockpit"}
   {:slot :cockpit
    :faction :forge
    :name "xf-25-cockpit"}
   {:slot :systems
    :faction :forge
    :name "maneuvering-fins"}
   {:slot :systems
    :faction :forge
    :name "falx-fins"}
   {:slot :systems
    :faction :forge
    :name "triple-tailfin"}
   {:slot :systems
    :faction :forge
    :name "xr-135-missile-rack"}
   {:slot :systems
    :faction :forge
    :name "xr70-missile-pod"}
   {:slot :systems
    :faction :forge
    :name "h.i.v.e-missile-pod"}
   {:slot :systems
    :faction :forge
    :name "sls-solar"}
   {:slot :systems
    :faction :forge
    :name "ls-eye"}
   {:slot :thruster
    :faction :forge
    :name "avb-burst-drive"}
   {:slot :thruster
    :faction :forge
    :name "falx-afterburner"}
   {:slot :thruster
    :faction :forge
    :name "falx-thruster"}
   {:slot :thruster
    :faction :forge
    :name "lance-thruster"}
   {:slot :thruster
    :faction :forge
    :name "sdu-14-jump-engine"}
   {:slot :thruster
    :faction :forge
    :name "sv-engine"}
   {:slot :systems
    :faction :forge
    :name "mark-48-shipbreaker"}
   {:slot :systems
    :faction :forge
    :name "medium-exhaust"}
   {:slot :systems
    :faction :forge
    :name "falx-nose"}
   {:slot :systems
    :faction :forge
    :name "small-exhaust"}
   {:slot :systems
    :faction :forge
    :name "scar-4-pulse-laser"}
   {:slot :systems
    :faction :forge
    :name "mk16-autocannon"}
   {:slot :systems
    :faction :forge
    :name "tdp-8-plasma-cannon"}
   {:slot :systems
    :faction :forge
    :name "mx-massdriver-shotgun"}
   {:slot :wing
    :faction :forge
    :name "sc-19-wings"}
   {:slot :wing
    :faction :forge
    :name "falx-wings"}
   {:slot :wing
    :faction :forge
    :name "xf-25-wings"}
   {:slot :wing
    :faction :forge
    :name "xj-7-wings"}
   {:slot :systems
    :faction :komplex
    :name "kp-bulkhead"}
   {:slot :systems
    :faction :komplex
    :name "bz2-booster"}
   {:slot :cockpit
    :faction :komplex
    :name "locust-cockpit"}
   {:slot :cockpit
    :faction :komplex
    :name "scarab-cockpit"}
   {:slot :cockpit
    :faction :komplex
    :name "wasp-cockpit"}
   {:slot :systems
    :faction :komplex
    :name "ecm-fin"}
   {:slot :systems
    :faction :komplex
    :name "maneuvering-fins"}
   {:slot :systems
    :faction :komplex
    :name "tailfin"}
   {:slot :systems
    :faction :komplex
    :name "dual-tailfin"}
   {:slot :systems
    :faction :komplex
    :name "tailfin-2"}
   {:slot :systems
    :faction :komplex
    :name "dual-tailfin-2"}
   {:slot :systems
    :faction :komplex
    :name "tailfin-3"}
   {:slot :systems
    :faction :komplex
    :name "cl-arm-rockets"}
   {:slot :systems
    :faction :komplex
    :name "zm2-heavy-missile"}
   {:slot :thruster
    :faction :komplex
    :name "stealth-drive"}
   {:slot :thruster
    :faction :komplex
    :name "fb3-tri-thruster"}
   {:slot :thruster
    :faction :komplex
    :name "scarab-thruster"}
   {:slot :thruster
    :faction :komplex
    :name "locust-thruster-mk2"}
   {:slot :thruster
    :faction :komplex
    :name "armored-thruster"}
   {:slot :thruster
    :faction :komplex
    :name "wasp-thruster-mk2"}
   {:slot :thruster
    :faction :komplex
    :name "wasp-thruster"}
   {:slot :systems
    :faction :komplex
    :name "cx6-anti-matter-torpedo"}
   {:slot :systems
    :faction :komplex
    :name "cx2-anti-ship-torpedo"}
   {:slot :systems
    :faction :komplex
    :name "k2-radiator-array"}
   {:slot :systems
    :faction :komplex
    :name "small-exhaust"}
   {:slot :systems
    :faction :komplex
    :name "medium-exhaust"}
   {:slot :systems
    :faction :komplex
    :name "kx-power-cycle"}
   {:slot :systems
    :faction :komplex
    :name "cl-23-railgun"}
   {:slot :systems
    :faction :komplex
    :name "c6-3-gatling-gun"}
   {:slot :systems
    :faction :komplex
    :name "c-t22-he-cannon"}
   {:slot :systems
    :faction :komplex
    :name "db8-proton-cannon"}
   {:slot :systems
    :faction :komplex
    :name "cpl-3-ball-turret"}
   {:slot :systems
    :faction :komplex
    :name "wg5-grenade-cluster"}
   {:slot :systems
    :faction :komplex
    :name "cpl-2-mantis-laser"}
   {:slot :wing
    :faction :komplex
    :name "blade-wings"}
   {:slot :wing
    :faction :komplex
    :name "locust-wings"}
   {:slot :wing
    :faction :komplex
    :name "wsp2-wings"}
   {:slot :wing
    :faction :komplex
    :name "wsp1-wings"}
   {:slot :wing
    :faction :komplex
    :name "vector-wings"}
   ]
  )
