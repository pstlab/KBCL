DOMAIN t1_Domain {

	TEMPORAL_MODULE temporal_module = [0, 1000], 500;

	COMP_TYPE SingletonStateVariable t1_ChannelType (Channel_R2_L1(), Channel_R2_L2(), Channel_R2_L3(), Channel_R1_R2(), Channel_B_B(), Channel_R1_R3(), Channel_R1_L1(), Channel_L2_R1(), Channel_R1_R1(), Channel_L2_R2(), Channel_R1_L3(), Channel_L2_R3(), Channel_R1_L2(), Channel_R3_B(), Channel_L1_R1(), Channel_L1_R2(), Channel_B_L3(), Channel_L1_R3(), Channel_L1_B(), Channel_R2_R3(), Channel_R2_R1(), Channel_R2_R2(), Channel_L3_R2(), Channel_R3_L1(), Channel_L3_R3(), Channel_B_R1(), Channel_B_R2(), Channel_B_R3(), Channel_L3_R1(), Channel_L3_B(), Channel_L2_B(), Channel_R1_B(), Channel_L3_L1(), Channel_L3_L2(), Channel_B_L2(), Channel_R3_L3(), Channel_B_L1(), Channel_R3_L2(), Channel_L3_L3(), Channel_L2_L1(), Channel_L2_L2(), Channel_L2_L3(), Channel_R3_R1(), Channel_L1_L1(), Channel_R3_R2(), Channel_R3_R3(), Channel_L1_L3(), Channel_L1_L2(), Channel_R2_B(), Idle()) {

		VALUE Channel_R2_L1() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R2_L2() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_R2_L3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_R2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_B_B() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_R3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_L1() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_R1() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_R1() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_R2() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_L3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_R3() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_L2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_B() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_R1() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_R2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_B_L3() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_R3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_B() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_R2_R3() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R2_R1() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R2_R2() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_R2() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_L1() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_R3() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_B_R1() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_B_R2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_B_R3() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_R1() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_B() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_B() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_B() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_L1() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_L2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_B_L2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_L3() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_B_L1() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_L2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_L3() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_L1() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_L2() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_L3() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_R1() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_L1() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_R2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_R3() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_L3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_L2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R2_B() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Idle() [1, 1000]
		MEETS {
			Channel_R2_L1();
			Channel_R2_L2();
			Channel_R2_L3();
			Channel_R1_R2();
			Channel_B_B();
			Channel_R1_R3();
			Channel_R1_L1();
			Channel_L2_R1();
			Channel_R1_R1();
			Channel_L2_R2();
			Channel_R1_L3();
			Channel_L2_R3();
			Channel_R1_L2();
			Channel_R3_B();
			Channel_L1_R1();
			Channel_L1_R2();
			Channel_B_L3();
			Channel_L1_R3();
			Channel_L1_B();
			Channel_R2_R3();
			Channel_R2_R1();
			Channel_R2_R2();
			Channel_L3_R2();
			Channel_R3_L1();
			Channel_L3_R3();
			Channel_B_R1();
			Channel_B_R2();
			Channel_B_R3();
			Channel_L3_R1();
			Channel_L3_B();
			Channel_L2_B();
			Channel_R1_B();
			Channel_L3_L1();
			Channel_L3_L2();
			Channel_B_L2();
			Channel_R3_L3();
			Channel_B_L1();
			Channel_R3_L2();
			Channel_L3_L3();
			Channel_L2_L1();
			Channel_L2_L2();
			Channel_L2_L3();
			Channel_R3_R1();
			Channel_L1_L1();
			Channel_R3_R2();
			Channel_R3_R3();
			Channel_L1_L3();
			Channel_L1_L2();
			Channel_R2_B();
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

	COMP_TYPE SingletonStateVariable t1_Port_BType (Port_B_Not_Available(), Port_B_Available()) {

		VALUE Port_B_Not_Available() [1, 1000]
		MEETS {
			Port_B_Available();
		}

		VALUE Port_B_Available() [1, 1000]
		MEETS {
			Port_B_Not_Available();
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

	COMP_TYPE SingletonStateVariable t1_Port_L2Type (Port_L2_Available(), Port_L2_Not_Available()) {

		VALUE Port_L2_Available() [1, 1000]
		MEETS {
			Port_L2_Not_Available();
		}

		VALUE Port_L2_Not_Available() [1, 1000]
		MEETS {
			Port_L2_Available();
		}

	}

	COMP_TYPE SingletonStateVariable t1_Port_R2Type (Port_R2_Not_Available(), Port_R2_Available()) {

		VALUE Port_R2_Not_Available() [1, 1000]
		MEETS {
			Port_R2_Available();
		}

		VALUE Port_R2_Available() [1, 1000]
		MEETS {
			Port_R2_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable t1_Port_L1Type (Port_L1_Not_Available(), Port_L1_Available()) {

		VALUE Port_L1_Not_Available() [1, 1000]
		MEETS {
			Port_L1_Available();
		}

		VALUE Port_L1_Available() [1, 1000]
		MEETS {
			Port_L1_Not_Available();
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

	COMP_TYPE SingletonStateVariable t1_Neighbor_BType (Neighbor_B_Available(), Neighbor_B_not_Available()) {

		VALUE Neighbor_B_Available() [1, 1000]
		MEETS {
			Neighbor_B_not_Available();
		}

		VALUE Neighbor_B_not_Available() [1, 1000]
		MEETS {
			Neighbor_B_Available();
		}

	}

	COMPONENT t1_Channel { FLEXIBLE t1_Channel_timeline(trex_internal_dispatch_asap) } : t1_ChannelType;
	COMPONENT t1_Port_R1 { FLEXIBLE t1_Port_R1_timeline(trex_internal_dispatch_asap) } : t1_Port_R1Type;
	COMPONENT t1_Port_B { FLEXIBLE t1_Port_B_timeline(trex_internal_dispatch_asap) } : t1_Port_BType;
	COMPONENT t1_Port_R3 { FLEXIBLE t1_Port_R3_timeline(trex_internal_dispatch_asap) } : t1_Port_R3Type;
	COMPONENT t1_Port_L3 { FLEXIBLE t1_Port_L3_timeline(trex_internal_dispatch_asap) } : t1_Port_L3Type;
	COMPONENT t1_Port_L2 { FLEXIBLE t1_Port_L2_timeline(trex_internal_dispatch_asap) } : t1_Port_L2Type;
	COMPONENT t1_Port_R2 { FLEXIBLE t1_Port_R2_timeline(trex_internal_dispatch_asap) } : t1_Port_R2Type;
	COMPONENT t1_Port_L1 { FLEXIBLE t1_Port_L1_timeline(trex_internal_dispatch_asap) } : t1_Port_L1Type;
	COMPONENT t1_Neighbor_L { FLEXIBLE t1_Neighbor_L_timeline(trex_internal_dispatch_asap) } : t1_Neighbor_LType;
	COMPONENT t1_Neighbor_R { FLEXIBLE t1_Neighbor_R_timeline(trex_internal_dispatch_asap) } : t1_Neighbor_RType;
	COMPONENT t1_Neighbor_B { FLEXIBLE t1_Neighbor_B_timeline(trex_internal_dispatch_asap) } : t1_Neighbor_BType;

	SYNCHRONIZE t1_Channel.t1_Channel_timeline {

		VALUE Channel_L2_R2() {

			cd0 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L3_L1() {

			cd0 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_B_R2() {

			cd0 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R2_R1() {

			cd0 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_R2_L1() {

			cd0 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L2_B() {

			cd0 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

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

		VALUE Channel_R2_B() {

			cd0 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R1_R2() {

			cd0 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_B_L2() {

			cd0 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_B_L3() {

			cd0 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L3_L3() {

			cd0 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
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

		VALUE Channel_L3_R2() {

			cd0 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
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

		VALUE Channel_R3_R1() {

			cd0 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_R3_L1() {

			cd0 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L2_R3() {

			cd0 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R1_B() {

			cd0 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L3_B() {

			cd0 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R2_R2() {

			cd0 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd1;

		}

		VALUE Channel_R3_R2() {

			cd0 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_R2_L2() {

			cd0 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L1_B() {

			cd0 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_B_B() {

			cd0 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			DURING [0,+INF] [0,+INF]  cd1;

		}

		VALUE Channel_R2_R3() {

			cd0 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_B_R3() {

			cd0 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R3_B() {

			cd0 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L2_L1() {

			cd0 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_L1_L2() {

			cd0 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_L2_R1() {

			cd0 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L1_R1() {

			cd0 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R1_L1() {

			cd0 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd3;

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

		VALUE Channel_L1_R2() {

			cd0 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R1_L2() {

			cd0 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
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

		VALUE Channel_R3_L2() {

			cd0 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R3_R3() {

			cd0 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd1;

		}

		VALUE Channel_B_L1() {

			cd0 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R2_L3() {

			cd0 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L1_R3() {

			cd0 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L3_L2() {

			cd0 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_B_R1() {

			cd0 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L2_L2() {

			cd0 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd1;

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

		VALUE Channel_L1_L1() {

			cd0 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd1;

		}

		VALUE Channel_L1_L3() {

			cd0 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_L2_L3() {

			cd0 t1_Port_L3.t1_Port_L3_timeline.Port_L3_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

	}

}

