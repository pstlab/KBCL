DOMAIN t1_Domain {

	TEMPORAL_MODULE temporal_module = [0, 1000], 500;

	COMP_TYPE SingletonStateVariable t1_ChannelType (Channel_L2_R1(), Channel_L2_R2(), Channel_L2_R3(), Channel_R3_B(), Channel_R3_F(), Channel_R2_F(), Channel_F_L2(), Channel_F_L1(), Channel_R1_F(), Channel_R2_R3(), Channel_R2_R1(), Channel_R2_R2(), Channel_R3_L1(), Channel_L2_F(), Channel_L2_B(), Channel_F_B(), Channel_B_L2(), Channel_R3_L2(), Channel_B_L1(), Channel_L2_L1(), Channel_L2_L2(), Channel_F_F(), Channel_L1_L1(), Channel_L1_L2(), Channel_R2_L1(), Channel_F_R1(), Channel_R2_L2(), Channel_F_R2(), Channel_R1_R2(), Channel_F_R3(), Channel_R1_R3(), Channel_B_B(), Channel_R1_L1(), Channel_R1_R1(), Channel_B_F(), Channel_R1_L2(), Channel_L1_R1(), Channel_L1_R2(), Channel_L1_B(), Channel_L1_R3(), Channel_L1_F(), Channel_B_R1(), Channel_B_R2(), Channel_B_R3(), Channel_R1_B(), Channel_R3_R1(), Channel_R3_R2(), Channel_R3_R3(), Idle(), Channel_R2_B()) {

		VALUE Channel_L2_R1() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_R2() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_R3() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_B() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_F() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R2_F() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_F_L2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_F_L1() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_F() [10, 25]
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

		VALUE Channel_R3_L1() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_F() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_B() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_F_B() [30, 60]
		MEETS {
			Idle();
		}

		VALUE Channel_B_L2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_L2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_B_L1() [10, 25]
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

		VALUE Channel_F_F() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_L1() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_L2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R2_L1() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_F_R1() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_R2_L2() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_F_R2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_R2() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_F_R3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_R3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_B_B() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_L1() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_R1() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_B_F() [30, 60]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_L2() [20, 35]
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

		VALUE Channel_L1_B() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_R3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_F() [10, 25]
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

		VALUE Channel_R1_B() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_R1() [40, 55]
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

		VALUE Idle() [1, 1000]
		MEETS {
			Channel_L2_R1();
			Channel_L2_R2();
			Channel_L2_R3();
			Channel_R3_B();
			Channel_R3_F();
			Channel_R2_F();
			Channel_F_L2();
			Channel_F_L1();
			Channel_R1_F();
			Channel_R2_R3();
			Channel_R2_R1();
			Channel_R2_R2();
			Channel_R3_L1();
			Channel_L2_F();
			Channel_L2_B();
			Channel_F_B();
			Channel_B_L2();
			Channel_R3_L2();
			Channel_B_L1();
			Channel_L2_L1();
			Channel_L2_L2();
			Channel_F_F();
			Channel_L1_L1();
			Channel_L1_L2();
			Channel_R2_L1();
			Channel_F_R1();
			Channel_R2_L2();
			Channel_F_R2();
			Channel_R1_R2();
			Channel_F_R3();
			Channel_B_B();
			Channel_R1_R3();
			Channel_R1_L1();
			Channel_R1_R1();
			Channel_B_F();
			Channel_R1_L2();
			Channel_L1_R1();
			Channel_L1_R2();
			Channel_L1_B();
			Channel_L1_R3();
			Channel_L1_F();
			Channel_B_R1();
			Channel_B_R2();
			Channel_B_R3();
			Channel_R1_B();
			Channel_R3_R1();
			Channel_R3_R2();
			Channel_R3_R3();
			Channel_R2_B();
		}

		VALUE Channel_R2_B() [20, 35]
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

	COMP_TYPE SingletonStateVariable t1_Cross_Transfer_2Type (Cross_2_Up(), Cross_2_Down(), Cross_2_Changing()) {

		VALUE Cross_2_Up() [1, 1000]
		MEETS {
			Cross_2_Changing();
		}

		VALUE Cross_2_Down() [1, 1000]
		MEETS {
			Cross_2_Changing();
		}

		VALUE Cross_2_Changing() [3, 3]
		MEETS {
			Cross_2_Up();
			Cross_2_Down();
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

	COMP_TYPE SingletonStateVariable t1_Cross_Conveyor_3Type (Cross_Conveyor_3_Still(), Cross_Conveyor_3_Forward(), Cross_Conveyor_3_Backward()) {

		VALUE Cross_Conveyor_3_Still() [1, 1000]
		MEETS {
			Cross_Conveyor_3_Forward();
			Cross_Conveyor_3_Backward();
		}

		VALUE Cross_Conveyor_3_Forward() [1, 1000]
		MEETS {
			Cross_Conveyor_3_Still();
		}

		VALUE Cross_Conveyor_3_Backward() [1, 1000]
		MEETS {
			Cross_Conveyor_3_Still();
		}

	}

	COMP_TYPE SingletonStateVariable t1_Cross_Transfer_1Type (Cross_1_Up(), Cross_1_Changing(), Cross_1_Down()) {

		VALUE Cross_1_Up() [1, 1000]
		MEETS {
			Cross_1_Changing();
		}

		VALUE Cross_1_Changing() [3, 3]
		MEETS {
			Cross_1_Up();
			Cross_1_Down();
		}

		VALUE Cross_1_Down() [1, 1000]
		MEETS {
			Cross_1_Changing();
		}

	}

	COMP_TYPE SingletonStateVariable t1_Main_ConveyorType (Main_Conveyor_Forward(), Main_Conveyor_Still(), Main_Conveyor_Backward()) {

		VALUE Main_Conveyor_Forward() [1, 1000]
		MEETS {
			Main_Conveyor_Still();
		}

		VALUE Main_Conveyor_Still() [1, 1000]
		MEETS {
			Main_Conveyor_Forward();
			Main_Conveyor_Backward();
		}

		VALUE Main_Conveyor_Backward() [1, 1000]
		MEETS {
			Main_Conveyor_Still();
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

	COMP_TYPE SingletonStateVariable t1_Port_FType (Port_F_Not_Available(), Port_F_Available()) {

		VALUE Port_F_Not_Available() [1, 1000]
		MEETS {
			Port_F_Available();
		}

		VALUE Port_F_Available() [1, 1000]
		MEETS {
			Port_F_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable t1_Cross_Conveyor_2Type (Cross_Conveyor_2_Forward(), Cross_Conveyor_2_Still(), Cross_Conveyor_2_Backward()) {

		VALUE Cross_Conveyor_2_Forward() [1, 1000]
		MEETS {
			Cross_Conveyor_2_Still();
		}

		VALUE Cross_Conveyor_2_Still() [1, 1000]
		MEETS {
			Cross_Conveyor_2_Forward();
			Cross_Conveyor_2_Backward();
		}

		VALUE Cross_Conveyor_2_Backward() [1, 1000]
		MEETS {
			Cross_Conveyor_2_Still();
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

	COMP_TYPE SingletonStateVariable t1_Cross_Conveyor_1Type (Cross_Conveyor_1_Still(), Cross_Conveyor_1_Forward(), Cross_Conveyor_1_Backward()) {

		VALUE Cross_Conveyor_1_Still() [1, 1000]
		MEETS {
			Cross_Conveyor_1_Forward();
			Cross_Conveyor_1_Backward();
		}

		VALUE Cross_Conveyor_1_Forward() [1, 1000]
		MEETS {
			Cross_Conveyor_1_Still();
		}

		VALUE Cross_Conveyor_1_Backward() [1, 1000]
		MEETS {
			Cross_Conveyor_1_Still();
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

	COMP_TYPE SingletonStateVariable t1_Cross_Transfer_3Type (Cross_3_Down(), Cross_3_Up(), Cross_3_Changing()) {

		VALUE Cross_3_Down() [1, 1000]
		MEETS {
			Cross_3_Changing();
		}

		VALUE Cross_3_Up() [1, 1000]
		MEETS {
			Cross_3_Changing();
		}

		VALUE Cross_3_Changing() [3, 3]
		MEETS {
			Cross_3_Down();
			Cross_3_Up();
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

	COMP_TYPE SingletonStateVariable t1_Neighbor_FType (Neighbor_F_not_Available(), Neighbor_F_Available()) {

		VALUE Neighbor_F_not_Available() [1, 1000]
		MEETS {
			Neighbor_F_Available();
		}

		VALUE Neighbor_F_Available() [1, 1000]
		MEETS {
			Neighbor_F_not_Available();
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
	COMPONENT t1_Cross_Transfer_2 { FLEXIBLE t1_Cross_Transfer_2_timeline(trex_internal_dispatch_asap) } : t1_Cross_Transfer_2Type;
	COMPONENT t1_Port_R3 { FLEXIBLE t1_Port_R3_timeline(trex_internal_dispatch_asap) } : t1_Port_R3Type;
	COMPONENT t1_Cross_Conveyor_3 { FLEXIBLE t1_Cross_Conveyor_3_timeline(trex_internal_dispatch_asap) } : t1_Cross_Conveyor_3Type;
	COMPONENT t1_Cross_Transfer_1 { FLEXIBLE t1_Cross_Transfer_1_timeline(trex_internal_dispatch_asap) } : t1_Cross_Transfer_1Type;
	COMPONENT t1_Main_Conveyor { FLEXIBLE t1_Main_Conveyor_timeline(trex_internal_dispatch_asap) } : t1_Main_ConveyorType;
	COMPONENT t1_Port_L2 { FLEXIBLE t1_Port_L2_timeline(trex_internal_dispatch_asap) } : t1_Port_L2Type;
	COMPONENT t1_Port_F { FLEXIBLE t1_Port_F_timeline(trex_internal_dispatch_asap) } : t1_Port_FType;
	COMPONENT t1_Cross_Conveyor_2 { FLEXIBLE t1_Cross_Conveyor_2_timeline(trex_internal_dispatch_asap) } : t1_Cross_Conveyor_2Type;
	COMPONENT t1_Port_R2 { FLEXIBLE t1_Port_R2_timeline(trex_internal_dispatch_asap) } : t1_Port_R2Type;
	COMPONENT t1_Cross_Conveyor_1 { FLEXIBLE t1_Cross_Conveyor_1_timeline(trex_internal_dispatch_asap) } : t1_Cross_Conveyor_1Type;
	COMPONENT t1_Port_L1 { FLEXIBLE t1_Port_L1_timeline(trex_internal_dispatch_asap) } : t1_Port_L1Type;
	COMPONENT t1_Cross_Transfer_3 { FLEXIBLE t1_Cross_Transfer_3_timeline(trex_internal_dispatch_asap) } : t1_Cross_Transfer_3Type;
	COMPONENT t1_Neighbor_L { FLEXIBLE t1_Neighbor_L_timeline(trex_internal_dispatch_asap) } : t1_Neighbor_LType;
	COMPONENT t1_Neighbor_R { FLEXIBLE t1_Neighbor_R_timeline(trex_internal_dispatch_asap) } : t1_Neighbor_RType;
	COMPONENT t1_Neighbor_F { FLEXIBLE t1_Neighbor_F_timeline(trex_internal_dispatch_asap) } : t1_Neighbor_FType;
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

		VALUE Channel_R2_F() {

			cd0 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

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

		VALUE Channel_F_R1() {

			cd0 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
			DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_F_R3() {

			cd0 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L2_F() {

			cd0 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_R1_R3() {

			cd0 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

		}

		VALUE Channel_F_L1() {

			cd0 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
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

		VALUE Channel_R3_F() {

			cd0 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_R3.t1_Port_R3_timeline.Port_R3_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
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

		VALUE Channel_F_F() {

			cd0 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
			DURING [0,+INF] [0,+INF]  cd1;

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

		VALUE Channel_F_B() {

			cd0 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_F_L2() {

			cd0 t1_Port_L2.t1_Port_L2_timeline.Port_L2_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_F_R2() {

			cd0 t1_Port_R2.t1_Port_R2_timeline.Port_R2_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd3;

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

		VALUE Channel_R1_F() {

			cd0 t1_Port_R1.t1_Port_R1_timeline.Port_R1_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_R.t1_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
			DURING [0,+INF] [0,+INF]  cd3;

		}

		VALUE Channel_L1_F() {

			cd0 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
			DURING [0,+INF] [0,+INF]  cd3;

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

		VALUE Channel_B_F() {

			cd0 t1_Port_F.t1_Port_F_timeline.Port_F_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_F.t1_Neighbor_F_timeline.Neighbor_F_Available();
			ENDS_DURING [0,+INF] [0,+INF]  cd1;

			cd2 t1_Port_B.t1_Port_B_timeline.Port_B_Available();
			STARTS_DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> t1_Neighbor_B.t1_Neighbor_B_timeline.Neighbor_B_Available();
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

		VALUE Channel_L1_L1() {

			cd0 t1_Port_L1.t1_Port_L1_timeline.Port_L1_Available();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> t1_Neighbor_L.t1_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd1;

		}

	}

}

