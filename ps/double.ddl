DOMAIN double_Domain {

	TEMPORAL_MODULE temporal_module = [0, 1000], 500;

	COMP_TYPE SingletonStateVariable double_ChannelType (Idle(), Channel_L1_F(), Channel_B_L1(), Channel_L1_L3(), Channel_L1_L1(), Channel_R1_B(), Channel_L1_R3(), Channel_R1_L3(), Channel_L1_R1(), Channel_R1_L1(), Channel_L3_F(), Channel_L1_B(), Channel_R1_R3(), Channel_F_L3(), Channel_B_F(), Channel_R3_L1(), Channel_R3_B(), Channel_F_R1(), Channel_B_B(), Channel_L3_L3(), Channel_F_R3(), Channel_R1_R1(), Channel_L3_L1(), Channel_F_L1(), Channel_R3_F(), Channel_R3_R3(), Channel_R3_R1(), Channel_R3_L3(), Channel_F_B(), Channel_L3_R1(), Channel_L3_R3(), Channel_B_R3(), Channel_R1_F(), Channel_F_F(), Channel_B_R1(), Channel_B_L3()) {

		VALUE Idle() [1, 1000]
		MEETS {
			Channel_L1_F();
			Channel_B_L1();
			Channel_L1_L3();
			Channel_L1_L1();
			Channel_R1_B();
			Channel_L1_R3();
			Channel_R1_L3();
			Channel_L1_R1();
			Channel_R1_L1();
			Channel_L3_F();
			Channel_L1_B();
			Channel_R1_R3();
			Channel_F_L3();
			Channel_R3_L1();
			Channel_B_F();
			Channel_R3_B();
			Channel_F_R1();
			Channel_B_B();
			Channel_L3_L3();
			Channel_F_R3();
			Channel_R1_R1();
			Channel_L3_L1();
			Channel_F_L1();
			Channel_R3_F();
			Channel_R3_R3();
			Channel_R3_R1();
			Channel_R3_L3();
			Channel_F_B();
			Channel_L3_R1();
			Channel_L3_R3();
			Channel_B_R3();
			Channel_R1_F();
			Channel_F_F();
			Channel_B_R1();
			Channel_B_L3();
		}

		VALUE Channel_L1_F() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_B_L1() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_L3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_L1() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_B() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_R3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_L3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_R1() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_L1() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_F() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L1_B() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_R3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_F_L3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_B_F() [30, 60]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_L1() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_B() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_F_R1() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_B_B() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_L3() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_F_R3() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_R1() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_L1() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_F_L1() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_F() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_R3() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_R1() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_R3_L3() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_F_B() [30, 60]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_R1() [40, 55]
		MEETS {
			Idle();
		}

		VALUE Channel_L3_R3() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_B_R3() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_R1_F() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_F_F() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_B_R1() [10, 25]
		MEETS {
			Idle();
		}

		VALUE Channel_B_L3() [10, 25]
		MEETS {
			Idle();
		}

	}

	COMP_TYPE SingletonStateVariable double_Main_ConveyorType (Main_Conveyor_Forward(), Main_Conveyor_Still(), Main_Conveyor_Backward()) {

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

	COMP_TYPE SingletonStateVariable double_Cross_Transfer_3Type (Cross_3_Down(), Cross_3_Changing(), Cross_3_Up()) {

		VALUE Cross_3_Down() [1, 1000]
		MEETS {
			Cross_3_Changing();
		}

		VALUE Cross_3_Changing() [3, 3]
		MEETS {
			Cross_3_Down();
			Cross_3_Up();
		}

		VALUE Cross_3_Up() [1, 1000]
		MEETS {
			Cross_3_Changing();
		}

	}

	COMP_TYPE SingletonStateVariable double_Cross_Conveyor_3Type (Cross_Conveyor_3_Forward(), Cross_Conveyor_3_Backward(), Cross_Conveyor_3_Still()) {

		VALUE Cross_Conveyor_3_Forward() [1, 1000]
		MEETS {
			Cross_Conveyor_3_Still();
		}

		VALUE Cross_Conveyor_3_Backward() [1, 1000]
		MEETS {
			Cross_Conveyor_3_Still();
		}

		VALUE Cross_Conveyor_3_Still() [1, 1000]
		MEETS {
			Cross_Conveyor_3_Forward();
			Cross_Conveyor_3_Backward();
		}

	}

	COMP_TYPE SingletonStateVariable double_Port_R1Type (Port_R1_Not_Available(), Port_R1_Available()) {

		VALUE Port_R1_Not_Available() [1, 1000]
		MEETS {
			Port_R1_Available();
		}

		VALUE Port_R1_Available() [1, 1000]
		MEETS {
			Port_R1_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable double_Port_FType (Port_F_Available(), Port_F_Not_Available()) {

		VALUE Port_F_Available() [1, 1000]
		MEETS {
			Port_F_Not_Available();
		}

		VALUE Port_F_Not_Available() [1, 1000]
		MEETS {
			Port_F_Available();
		}

	}

	COMP_TYPE SingletonStateVariable double_Port_BType (Port_B_Available(), Port_B_Not_Available()) {

		VALUE Port_B_Available() [1, 1000]
		MEETS {
			Port_B_Not_Available();
		}

		VALUE Port_B_Not_Available() [1, 1000]
		MEETS {
			Port_B_Available();
		}

	}

	COMP_TYPE SingletonStateVariable double_Port_L3Type (Port_L3_Not_Available(), Port_L3_Available()) {

		VALUE Port_L3_Not_Available() [1, 1000]
		MEETS {
			Port_L3_Available();
		}

		VALUE Port_L3_Available() [1, 1000]
		MEETS {
			Port_L3_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable double_Cross_Transfer_1Type (Cross_1_Up(), Cross_1_Changing(), Cross_1_Down()) {

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

	COMP_TYPE SingletonStateVariable double_Cross_Conveyor_1Type (Cross_Conveyor_1_Still(), Cross_Conveyor_1_Backward(), Cross_Conveyor_1_Forward()) {

		VALUE Cross_Conveyor_1_Still() [1, 1000]
		MEETS {
			Cross_Conveyor_1_Backward();
			Cross_Conveyor_1_Forward();
		}

		VALUE Cross_Conveyor_1_Backward() [1, 1000]
		MEETS {
			Cross_Conveyor_1_Still();
		}

		VALUE Cross_Conveyor_1_Forward() [1, 1000]
		MEETS {
			Cross_Conveyor_1_Still();
		}

	}

	COMP_TYPE SingletonStateVariable double_Port_R3Type (Port_R3_Not_Available(), Port_R3_Available()) {

		VALUE Port_R3_Not_Available() [1, 1000]
		MEETS {
			Port_R3_Available();
		}

		VALUE Port_R3_Available() [1, 1000]
		MEETS {
			Port_R3_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable double_Cross_Transfer_2Type (Cross_2_Up(), Cross_2_Down(), Cross_2_Changing()) {

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

	COMP_TYPE SingletonStateVariable double_Port_L1Type (Port_L1_Not_Available(), Port_L1_Available()) {

		VALUE Port_L1_Not_Available() [1, 1000]
		MEETS {
			Port_L1_Available();
		}

		VALUE Port_L1_Available() [1, 1000]
		MEETS {
			Port_L1_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable double_Neighbor_RType (Neighbor_R_not_Available(), Neighbor_R_Available()) {

		VALUE Neighbor_R_not_Available() [1, 1000]
		MEETS {
			Neighbor_R_Available();
		}

		VALUE Neighbor_R_Available() [1, 1000]
		MEETS {
			Neighbor_R_not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable double_Neighbor_FType (Neighbor_F_not_Available(), Neighbor_F_Available()) {

		VALUE Neighbor_F_not_Available() [1, 1000]
		MEETS {
			Neighbor_F_Available();
		}

		VALUE Neighbor_F_Available() [1, 1000]
		MEETS {
			Neighbor_F_not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable double_Neighbor_LType (Neighbor_L_Available(), Neighbor_L_not_Available()) {

		VALUE Neighbor_L_Available() [1, 1000]
		MEETS {
			Neighbor_L_not_Available();
		}

		VALUE Neighbor_L_not_Available() [1, 1000]
		MEETS {
			Neighbor_L_Available();
		}

	}

	COMP_TYPE SingletonStateVariable double_Neighbor_BType (Neighbor_B_Available(), Neighbor_B_not_Available()) {

		VALUE Neighbor_B_Available() [1, 1000]
		MEETS {
			Neighbor_B_not_Available();
		}

		VALUE Neighbor_B_not_Available() [1, 1000]
		MEETS {
			Neighbor_B_Available();
		}

	}

	COMPONENT double_Channel { FLEXIBLE double_Channel_timeline(trex_internal_dispatch_asap) } : double_ChannelType;
	COMPONENT double_Main_Conveyor { FLEXIBLE double_Main_Conveyor_timeline(trex_internal_dispatch_asap) } : double_Main_ConveyorType;
	COMPONENT double_Cross_Transfer_3 { FLEXIBLE double_Cross_Transfer_3_timeline(trex_internal_dispatch_asap) } : double_Cross_Transfer_3Type;
	COMPONENT double_Cross_Conveyor_3 { FLEXIBLE double_Cross_Conveyor_3_timeline(trex_internal_dispatch_asap) } : double_Cross_Conveyor_3Type;
	COMPONENT double_Port_R1 { FLEXIBLE double_Port_R1_timeline(trex_internal_dispatch_asap) } : double_Port_R1Type;
	COMPONENT double_Port_F { FLEXIBLE double_Port_F_timeline(trex_internal_dispatch_asap) } : double_Port_FType;
	COMPONENT double_Port_B { FLEXIBLE double_Port_B_timeline(trex_internal_dispatch_asap) } : double_Port_BType;
	COMPONENT double_Port_L3 { FLEXIBLE double_Port_L3_timeline(trex_internal_dispatch_asap) } : double_Port_L3Type;
	COMPONENT double_Cross_Transfer_1 { FLEXIBLE double_Cross_Transfer_1_timeline(trex_internal_dispatch_asap) } : double_Cross_Transfer_1Type;
	COMPONENT double_Cross_Conveyor_1 { FLEXIBLE double_Cross_Conveyor_1_timeline(trex_internal_dispatch_asap) } : double_Cross_Conveyor_1Type;
	COMPONENT double_Port_R3 { FLEXIBLE double_Port_R3_timeline(trex_internal_dispatch_asap) } : double_Port_R3Type;
	COMPONENT double_Cross_Transfer_2 { FLEXIBLE double_Cross_Transfer_2_timeline(trex_internal_dispatch_asap) } : double_Cross_Transfer_2Type;
	COMPONENT double_Port_L1 { FLEXIBLE double_Port_L1_timeline(trex_internal_dispatch_asap) } : double_Port_L1Type;
	COMPONENT double_Neighbor_R { FLEXIBLE double_Neighbor_R_timeline(uncontrollable) } : double_Neighbor_RType;
	COMPONENT double_Neighbor_F { FLEXIBLE double_Neighbor_F_timeline(uncontrollable) } : double_Neighbor_FType;
	COMPONENT double_Neighbor_L { FLEXIBLE double_Neighbor_L_timeline(uncontrollable) } : double_Neighbor_LType;
	COMPONENT double_Neighbor_B { FLEXIBLE double_Neighbor_B_timeline(uncontrollable) } : double_Neighbor_BType;

	SYNCHRONIZE double_Channel.double_Channel_timeline {

		VALUE Channel_R1_L1() {

			cd0 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Forward();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Port_R1.double_Port_R1_timeline.Port_R1_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_L1.double_Port_L1_timeline.Port_L1_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd5;

		}

		VALUE Channel_L1_R1() {

			cd0 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Forward();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Port_R1.double_Port_R1_timeline.Port_R1_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_L1.double_Port_L1_timeline.Port_L1_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd5;

		}

		VALUE Channel_L1_B() {

			cd0  <?> double_Neighbor_B.double_Neighbor_B_timeline.Neighbor_B_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_B.double_Port_B_timeline.Port_B_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Port_L1.double_Port_L1_timeline.Port_L1_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			CONTAINS [0,+INF] [0,+INF]  cd9;

			cd10 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd10;

		}

		VALUE Channel_F_L1() {

			cd0 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_L1.double_Port_L1_timeline.Port_L1_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Port_F.double_Port_F_timeline.Port_F_Available();
			DURING [0,+INF] [0,+INF]  cd5;

			cd6  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd6;

			cd7  <?> double_Neighbor_F.double_Neighbor_F_timeline.Neighbor_F_Available();
			DURING [0,+INF] [0,+INF]  cd7;

		}

		VALUE Channel_R1_B() {

			cd0  <?> double_Neighbor_B.double_Neighbor_B_timeline.Neighbor_B_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Port_B.double_Port_B_timeline.Port_B_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Port_R1.double_Port_R1_timeline.Port_R1_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			CONTAINS [0,+INF] [0,+INF]  cd9;

			cd10 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd10;

		}

		VALUE Channel_R3_L3() {

			cd0 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Forward();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Port_R3.double_Port_R3_timeline.Port_R3_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_L3.double_Port_L3_timeline.Port_L3_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd5;

		}

		VALUE Channel_R3_L1() {

			cd0 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_L1.double_Port_L1_timeline.Port_L1_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_R3.double_Port_R3_timeline.Port_R3_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			CONTAINS [0,+INF] [0,+INF]  cd9;

			cd10 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd10;

		}

		VALUE Channel_B_R1() {

			cd0 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Port_R1.double_Port_R1_timeline.Port_R1_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_B.double_Port_B_timeline.Port_B_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5  <?> double_Neighbor_B.double_Neighbor_B_timeline.Neighbor_B_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			CONTAINS [0,+INF] [0,+INF]  cd9;

			cd10 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd10;

		}

		VALUE Channel_R3_R1() {

			cd0 double_Port_R1.double_Port_R1_timeline.Port_R1_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_R3.double_Port_R3_timeline.Port_R3_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			CONTAINS [0,+INF] [0,+INF]  cd6;

			cd7 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			CONTAINS [0,+INF] [0,+INF]  cd7;

			cd8  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd8;

			cd9 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd9;

		}

		VALUE Channel_R1_L3() {

			cd0 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Port_L3.double_Port_L3_timeline.Port_L3_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Port_R1.double_Port_R1_timeline.Port_R1_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			CONTAINS [0,+INF] [0,+INF]  cd9;

			cd10 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd10;

		}

		VALUE Channel_F_R1() {

			cd0 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_R1.double_Port_R1_timeline.Port_R1_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Port_F.double_Port_F_timeline.Port_F_Available();
			DURING [0,+INF] [0,+INF]  cd5;

			cd6  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd6;

			cd7  <?> double_Neighbor_F.double_Neighbor_F_timeline.Neighbor_F_Available();
			DURING [0,+INF] [0,+INF]  cd7;

		}

		VALUE Channel_B_R3() {

			cd0 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_R3.double_Port_R3_timeline.Port_R3_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Port_B.double_Port_B_timeline.Port_B_Available();
			DURING [0,+INF] [0,+INF]  cd5;

			cd6  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd6;

			cd7  <?> double_Neighbor_B.double_Neighbor_B_timeline.Neighbor_B_Available();
			DURING [0,+INF] [0,+INF]  cd7;

		}

		VALUE Channel_R3_F() {

			cd0  <?> double_Neighbor_F.double_Neighbor_F_timeline.Neighbor_F_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_F.double_Port_F_timeline.Port_F_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_R3.double_Port_R3_timeline.Port_R3_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			CONTAINS [0,+INF] [0,+INF]  cd9;

			cd10 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd10;

		}

		VALUE Channel_L1_L3() {

			cd0 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Port_L3.double_Port_L3_timeline.Port_L3_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_L1.double_Port_L1_timeline.Port_L1_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			CONTAINS [0,+INF] [0,+INF]  cd6;

			cd7 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			CONTAINS [0,+INF] [0,+INF]  cd7;

			cd8  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd8;

			cd9 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd9;

		}

		VALUE Channel_R1_F() {

			cd0 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_R1.double_Port_R1_timeline.Port_R1_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Port_F.double_Port_F_timeline.Port_F_Available();
			DURING [0,+INF] [0,+INF]  cd5;

			cd6  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd6;

			cd7  <?> double_Neighbor_F.double_Neighbor_F_timeline.Neighbor_F_Available();
			DURING [0,+INF] [0,+INF]  cd7;

		}

		VALUE Channel_L1_L1() {

			cd0 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_L1.double_Port_L1_timeline.Port_L1_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd4;

		}

		VALUE Channel_L3_F() {

			cd0 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> double_Neighbor_F.double_Neighbor_F_timeline.Neighbor_F_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_F.double_Port_F_timeline.Port_F_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Port_L3.double_Port_L3_timeline.Port_L3_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			CONTAINS [0,+INF] [0,+INF]  cd9;

			cd10 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd10;

		}

		VALUE Channel_B_L1() {

			cd0 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_L1.double_Port_L1_timeline.Port_L1_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6  <?> double_Neighbor_B.double_Neighbor_B_timeline.Neighbor_B_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7 double_Port_B.double_Port_B_timeline.Port_B_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			CONTAINS [0,+INF] [0,+INF]  cd9;

			cd10 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd10;

		}

		VALUE Channel_R3_B() {

			cd0 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_R3.double_Port_R3_timeline.Port_R3_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Port_B.double_Port_B_timeline.Port_B_Available();
			DURING [0,+INF] [0,+INF]  cd5;

			cd6  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd6;

			cd7  <?> double_Neighbor_B.double_Neighbor_B_timeline.Neighbor_B_Available();
			DURING [0,+INF] [0,+INF]  cd7;

		}

		VALUE Channel_R1_R1() {

			cd0 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_R1.double_Port_R1_timeline.Port_R1_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd4;

		}

		VALUE Channel_L3_L3() {

			cd0 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_L3.double_Port_L3_timeline.Port_L3_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd4;

		}

		VALUE Channel_F_F() {

			cd0 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_F.double_Port_F_timeline.Port_F_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_F.double_Neighbor_F_timeline.Neighbor_F_Available();
			DURING [0,+INF] [0,+INF]  cd4;

		}

		VALUE Channel_B_L3() {

			cd0 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_L3.double_Port_L3_timeline.Port_L3_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Port_B.double_Port_B_timeline.Port_B_Available();
			DURING [0,+INF] [0,+INF]  cd5;

			cd6  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd6;

			cd7  <?> double_Neighbor_B.double_Neighbor_B_timeline.Neighbor_B_Available();
			DURING [0,+INF] [0,+INF]  cd7;

		}

		VALUE Channel_B_F() {

			cd0 double_Port_F.double_Port_F_timeline.Port_F_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> double_Neighbor_F.double_Neighbor_F_timeline.Neighbor_F_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Port_B.double_Port_B_timeline.Port_B_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> double_Neighbor_B.double_Neighbor_B_timeline.Neighbor_B_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd6;

			cd7 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			DURING [0,+INF] [0,+INF]  cd7;

		}

		VALUE Channel_L3_R3() {

			cd0 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Forward();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Port_R3.double_Port_R3_timeline.Port_R3_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_L3.double_Port_L3_timeline.Port_L3_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd5;

		}

		VALUE Channel_L3_R1() {

			cd0 double_Port_R1.double_Port_R1_timeline.Port_R1_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Port_L3.double_Port_L3_timeline.Port_L3_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			CONTAINS [0,+INF] [0,+INF]  cd9;

			cd10 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd10;

		}

		VALUE Channel_L1_R3() {

			cd0 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Port_R3.double_Port_R3_timeline.Port_R3_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Port_L1.double_Port_L1_timeline.Port_L1_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			CONTAINS [0,+INF] [0,+INF]  cd9;

			cd10 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd10;

		}

		VALUE Channel_L1_F() {

			cd0 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_L1.double_Port_L1_timeline.Port_L1_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Port_F.double_Port_F_timeline.Port_F_Available();
			DURING [0,+INF] [0,+INF]  cd5;

			cd6  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd6;

			cd7  <?> double_Neighbor_F.double_Neighbor_F_timeline.Neighbor_F_Available();
			DURING [0,+INF] [0,+INF]  cd7;

		}

		VALUE Channel_F_L3() {

			cd0  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Port_L3.double_Port_L3_timeline.Port_L3_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_F.double_Port_F_timeline.Port_F_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6  <?> double_Neighbor_F.double_Neighbor_F_timeline.Neighbor_F_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			CONTAINS [0,+INF] [0,+INF]  cd9;

			cd10 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd10;

		}

		VALUE Channel_R1_R3() {

			cd0 double_Port_R3.double_Port_R3_timeline.Port_R3_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_R1.double_Port_R1_timeline.Port_R1_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			CONTAINS [0,+INF] [0,+INF]  cd6;

			cd7 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			CONTAINS [0,+INF] [0,+INF]  cd7;

			cd8 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd8;

			cd9  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd9;

		}

		VALUE Channel_L3_L1() {

			cd0 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Conveyor_1.double_Cross_Conveyor_1_timeline.Cross_Conveyor_1_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Port_L1.double_Port_L1_timeline.Port_L1_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_L3.double_Port_L3_timeline.Port_L3_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			CONTAINS [0,+INF] [0,+INF]  cd6;

			cd7 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			CONTAINS [0,+INF] [0,+INF]  cd7;

			cd8 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd8;

			cd9  <?> double_Neighbor_L.double_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd9;

		}

		VALUE Channel_R3_R3() {

			cd0 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_R3.double_Port_R3_timeline.Port_R3_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd4;

		}

		VALUE Channel_F_B() {

			cd0 double_Port_B.double_Port_B_timeline.Port_B_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> double_Neighbor_B.double_Neighbor_B_timeline.Neighbor_B_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Port_F.double_Port_F_timeline.Port_F_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> double_Neighbor_F.double_Neighbor_F_timeline.Neighbor_F_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			DURING [0,+INF] [0,+INF]  cd5;

			cd6 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd6;

			cd7 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			DURING [0,+INF] [0,+INF]  cd7;

		}

		VALUE Channel_F_R3() {

			cd0 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Cross_Conveyor_3.double_Cross_Conveyor_3_timeline.Cross_Conveyor_3_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2  <?> double_Neighbor_R.double_Neighbor_R_timeline.Neighbor_R_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_R3.double_Port_R3_timeline.Port_R3_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 double_Port_F.double_Port_F_timeline.Port_F_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 double_Cross_Transfer_1.double_Cross_Transfer_1_timeline.Cross_1_Down();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6  <?> double_Neighbor_F.double_Neighbor_F_timeline.Neighbor_F_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			CONTAINS [0,+INF] [0,+INF]  cd9;

			cd10 double_Cross_Transfer_2.double_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd10;

		}

		VALUE Channel_B_B() {

			cd0 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 double_Main_Conveyor.double_Main_Conveyor_timeline.Main_Conveyor_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 double_Cross_Transfer_3.double_Cross_Transfer_3_timeline.Cross_3_Down();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 double_Port_B.double_Port_B_timeline.Port_B_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> double_Neighbor_B.double_Neighbor_B_timeline.Neighbor_B_Available();
			DURING [0,+INF] [0,+INF]  cd4;

		}

	}

}

