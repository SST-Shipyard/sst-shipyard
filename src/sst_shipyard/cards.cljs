(ns sst-shipyard.cards)

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

   {:faction :forge
    :ship "RL-55"
    :type "Assault Drone"
    :points 2
    :slots [:wing :systems :systems]
    }
   {:faction :forge
    :ship "DA-63"
    :type "Turbine Drone"
    :points 2
    :slots [:thruster :systems :systems]
    }
   {:faction :forge
    :ship "SR-12"
    :type "Recon Drone"
    :points 2
    :slots [:wing :systems :systems]
    }
   {:faction :forge
    :ship "AV-2"
    :type "Protector Drone"
    :points 2
    :slots [:thruster :systems :systems]
    }

   {:faction :komplex
    :ship "K-DIVE"
    :type "Ambush Drone"
    :points 2
    :slots [:wing :systems :systems]
    }
   {:faction :komplex
    :ship "K-SWARM"
    :type "Strike Drone"
    :points 2
    :slots [:wing :systems :systems]
    }
   {:faction :komplex
    :ship "K-HEAT"
    :type "Ramora Drone"
    :points 2
    :slots [:wing :systems :systems]
    }
   {:faction :komplex
    :ship "K-JAM"
    :type "Disruption Drone"
    :points 2
    :slots [:thruster :systems :systems]
    }
   ]
  )

(def parts
  [
   {:slot :utility
    :faction :forge
    :name "bf-bulkhead"}
   {:slot :utility
    :faction :forge
    :name "lance-booster"}
   {:slot :utility
    :faction :forge
    :name "falx-booster"}
   {:slot :utility
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
   {:slot :fin
    :faction :forge
    :name "maneuvering-fins"}
   {:slot :fin
    :faction :forge
    :name "falx-fins"}
   {:slot :fin
    :faction :forge
    :name "triple-tailfin"}
   {:slot :missile
    :faction :forge
    :name "xr-135-missile-rack"}
   {:slot :missile
    :faction :forge
    :name "xr70-missile-pod"}
   {:slot :missile
    :faction :forge
    :name "h.i.v.e-missile-pod"}
   {:slot :vent
    :faction :forge
    :name "sls-solar"}
   {:slot :utility
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
   {:slot :missile
    :faction :forge
    :name "mark-48-shipbreaker"}
   {:slot :vent
    :faction :forge
    :name "medium-exhaust"}
   {:slot :vent
    :faction :forge
    :name "falx-nose"}
   {:slot :vent
    :faction :forge
    :name "small-exhaust"}
   {:slot :weapon
    :faction :forge
    :name "scar-4-pulse-laser"}
   {:slot :weapon
    :faction :forge
    :name "mk16-autocannon"}
   {:slot :weapon
    :faction :forge
    :name "tdp-8-plasma-cannon"}
   {:slot :weapon
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
   {:slot :utility
    :faction :komplex
    :name "kp-bulkhead"}
   {:slot :utility
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
   {:slot :utility
    :faction :komplex
    :name "ecm-fin"}
   {:slot :fin
    :faction :komplex
    :name "maneuvering-fins"}
   {:slot :fin
    :faction :komplex
    :name "tailfin"}
   {:slot :fin
    :faction :komplex
    :name "dual-tailfin"}
   {:slot :fin
    :faction :komplex
    :name "tailfin-2"}
   {:slot :fin
    :faction :komplex
    :name "dual-tailfin-2"}
   {:slot :fin
    :faction :komplex
    :name "tailfin-3"}
   {:slot :missile
    :faction :komplex
    :name "cl-arm-rockets"}
   {:slot :missile
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
   {:slot :missile
    :faction :komplex
    :name "cx6-anti-matter-torpedo"}
   {:slot :missile
    :faction :komplex
    :name "cx2-anti-ship-torpedo"}
   {:slot :vent
    :faction :komplex
    :name "k2-radiator-array"}
   {:slot :vent
    :faction :komplex
    :name "small-exhaust"}
   {:slot :vent
    :faction :komplex
    :name "medium-exhaust"}
   {:slot :vent
    :faction :komplex
    :name "kx-power-cycle"}
   {:slot :weapon
    :faction :komplex
    :name "cl-23-railgun"}
   {:slot :weapon
    :faction :komplex
    :name "c6-3-gatling-gun"}
   {:slot :weapon
    :faction :komplex
    :name "c-t22-he-cannon"}
   {:slot :weapon
    :faction :komplex
    :name "db8-proton-cannon"}
   {:slot :weapon
    :faction :komplex
    :name "cpl-3-ball-turret"}
   {:slot :weapon
    :faction :komplex
    :name "wg5-grenade-cluster"}
   {:slot :weapon
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

   {:slot :cockpit
    :faction :forge
    :name "gunship-cockpit"}
   {:slot :thruster
    :faction :forge
    :name "da-turbine-thruster"}
   {:slot :wing
    :faction :forge
    :name "rl-55-wings"}
   {:slot :weapon
    :faction :forge
    :name "scar-2-pulse-laser"}
   {:slot :weapon
    :faction :forge
    :name "mk3-plasma-cannon"}
   {:slot :weapon
    :faction :forge
    :name "x1-sniper-laser"}
   {:slot :weapon
    :faction :forge
    :name "m5-metal-storm"}
   {:slot :fin
    :faction :forge
    :name "strafing-fins"}
   {:slot :fin
    :faction :forge
    :name "dual-tailfin"}
   {:slot :utility
    :faction :forge
    :name "targeting-array"}
   {:slot :utility
    :faction :forge
    :name "small-deflector"}
   {:slot :utility
    :faction :forge
    :name "jamming-fin"}
   {:slot :utility
    :faction :forge
    :name "missile-relay"}

   {:slot :cockpit
    :faction :komplex
    :name "reaper-cockpit"}
   {:slot :thruster
    :faction :komplex
    :name "thruster-fin"}
   {:slot :wing
    :faction :komplex
    :name "kd1-dual-wings"}
   {:slot :wing
    :faction :komplex
    :name "swarm-wings"}
   {:slot :weapon
    :faction :komplex
    :name "c-8-gauss-cannon"}
   {:slot :weapon
    :faction :komplex
    :name "cdl-shock"}
   {:slot :fin
    :faction :komplex
    :name "armored-tailfin"}
   {:slot :utility
    :faction :komplex
    :name "adaptive-plating"}
   {:slot :utility
    :faction :komplex
    :name "xm-booster-pods"}
   {:slot :utility
    :faction :komplex
    :name "k2-small-deflector"}
   {:slot :utility
    :faction :komplex
    :name "ram-spike"}
   {:slot :utility
    :faction :komplex
    :name "hrk-lockdown"}
   {:slot :utility
    :faction :komplex
    :name "s-8-exact"}
   ]
  )
