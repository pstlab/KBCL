DOMAIN t1_Domain {

	TEMPORAL_MODULE temporal_module = [0, 1000], 500;

	COMP_TYPE SingletonStateVariable t1_ChannelType (Channel_L3_R3(), Channel_R1_R3(), Channel_R1_R1(), Channel_R1_L3(), Channel_L3_R1(), Channel_R3_R1(), Channel_R3_R3(), Idle(), Channel_R3_L3(), Channel_L3_L3()) {

		VALUE Channel_L3_R3() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_R3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_R1() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_L3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_R1() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_R1() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_R3() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Idle() [1, 1000]
		MEETS {
			Channel_L3_R3();
			Channel_R1_R3();
			Channel_R1_R1();
			Channel_R1_L3();
			Channel_L3_R1();
			Channel_R3_R1();
			Channel_R3_R3();
			Channel_R3_L3();
			Channel_L3_L3();
		}

		VALUE Channel_R3_L3() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_L3() [10, 20]
		MEETS {
			Idle();
		}

	}

	COMP_TYPE SingletonStateVariable t1_Port_R1Type (Port_R1_Available(), Port_R1_Not_Available()) {

		VALUE Port_R1_Available() [1, 1000]
		MEETS {
			Port_R1_Not_Available();
		}

		VALUE Port_R1_Not_Available() [1, 1000]
		MEETS {
			Port_R1_Available();
		}

	}

	COMP_TYPE SingletonStateVariable t1_Port_R3Type (Port_R3_Not_Available(), Port_R3_Available()) {

		VALUE Port_R3_Not_Available() [1, 1000]
		MEETS {
			Port_R3_Available();
		}

		VALUE Port_R3_Available() [1, 1000]
		MEETS {
			Port_R3_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable t1_Port_L3Type (Port_L3_Not_Available(), Port_L3_Available()) {

		VALUE Port_L3_Not_Available() [1, 1000]
		MEETS {
			Port_L3_Available();
		}

		VALUE Port_L3_Available() [1, 1000]
		MEETS {
			Port_L3_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable t1_Neighbor_RType (Neighbor_R_Available(), Neighbor_R_not_Available()) {

		VALUE Neighbor_R_Available() [1, 1000]
		MEETS {
			Neighbor_R_not_Available();
		}

		VALUE Neighbor_R_not_Available() [1, 1000]
		MEETS {
			Neighbor_R_Available();
		}

	}

	COMP_TYPE SingletonStateVariable t1_Neighbor_LType (Neighbor_L_not_Available(), Neighbor_L_Available()) {

		VALUE Neighbor_L_not_Available() [1, 1000]
		MEETS {
			Neighbor_L_Available();
		}

		VALUE Neighbor_L_Available() [1, 1000]
		MEETS {
			Neighbor_L_not_Available();
		}

	}

	COMPONENT t1_Channel { FLEXIBLE t1_Channel_timeline(trex_internal_dispatch_asap) } : t1_ChannelType;
	COMPONENT t1_Port_R1 { FLEXIBLE t1_Port_R1_timeline(trex_internal_dispatch_asap) } : t1_Port_R1Type;
	COMPONENT t1_Port_R3 { FLEXIBLE t1_Port_R3_timeline(trex_internal_dispatch_asap) } : t1_Port_R3Type;
	COMPONENT t1_Port_L3 { FLEXIBLE t1_Port_L3_timeline(trex_internal_dispatch_asap) } : t1_Port_L3Type;
	COMPONENT t1_Neighbor_R { FLEXIBLE t1_Neighbor_R_timeline(trex_internal_dispatch_asap) } : t1_Neighbor_RType;
	COMPONENT t1_Neighbor_L { FLEXIBLE t1_Neighbor_L_timeline(trex_internal_dispatch_asap) } : t1_Neighbor_LType;

	SYNCHRONIZE t1_Channel.t1_Channel_timeline {

		VALUE Channel_R3_R3() {

			cd0 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd1;

		}

		VALUE Channel_R3_L3() {

			cd0 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R3_R1() {

			cd0 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_L3_R3() {

			cd0 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L3_R1() {

			cd0 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R1_L3() {

			cd0 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R1_R1() {

			cd0 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd1;

		}

		VALUE Channel_R1_R3() {

			cd0 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_L3_L3() {

			cd0 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd1;

		}

	}

}

