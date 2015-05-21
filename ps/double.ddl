DOMAIN double_Domain {

	TEMPORAL_MODULE temporal_module = [0, 1000], 500;

	COMP_TYPE SingletonStateVariable double_ChannelType (Channel_F_B(), Idle(), Channel_B_F(), Channel_B_B(), Channel_F_F()) {

		VALUE Channel_F_B() [30, 60]
		MEETS {
			Idle();
		}

		VALUE Idle() [1, 1000]
		MEETS {
			Channel_F_B();
			Channel_B_F();
			Channel_B_B();
			Channel_F_F();
		}

		VALUE Channel_B_F() [30, 60]
		MEETS {
			Idle();
		}

		VALUE Channel_B_B() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_F_F() [10, 20]
		MEETS {
			Idle();
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

	COMPONENT double_Channel { FLEXIBLE double_Channel_timeline(trex_internal_dispatch_asap) } : double_ChannelType;
	COMPONENT double_Port_R1 { FLEXIBLE double_Port_R1_timeline(trex_internal_dispatch_asap) } : double_Port_R1Type;
	COMPONENT double_Port_L3 { FLEXIBLE double_Port_L3_timeline(trex_internal_dispatch_asap) } : double_Port_L3Type;
	COMPONENT double_Port_B { FLEXIBLE double_Port_B_timeline(trex_internal_dispatch_asap) } : double_Port_BType;
	COMPONENT double_Cross_Transfer_2 { FLEXIBLE double_Cross_Transfer_2_timeline(trex_internal_dispatch_asap) } : double_Cross_Transfer_2Type;
	COMPONENT double_Port_F { FLEXIBLE double_Port_F_timeline(trex_internal_dispatch_asap) } : double_Port_FType;
	COMPONENT double_Cross_Transfer_3 { FLEXIBLE double_Cross_Transfer_3_timeline(trex_internal_dispatch_asap) } : double_Cross_Transfer_3Type;
	COMPONENT double_Port_L1 { FLEXIBLE double_Port_L1_timeline(trex_internal_dispatch_asap) } : double_Port_L1Type;
	COMPONENT double_Cross_Transfer_1 { FLEXIBLE double_Cross_Transfer_1_timeline(trex_internal_dispatch_asap) } : double_Cross_Transfer_1Type;
	COMPONENT double_Main_Conveyor { FLEXIBLE double_Main_Conveyor_timeline(trex_internal_dispatch_asap) } : double_Main_ConveyorType;
	COMPONENT double_Port_R3 { FLEXIBLE double_Port_R3_timeline(trex_internal_dispatch_asap) } : double_Port_R3Type;
	COMPONENT double_Neighbor_B { FLEXIBLE double_Neighbor_B_timeline(uncontrollable) } : double_Neighbor_BType;
	COMPONENT double_Neighbor_F { FLEXIBLE double_Neighbor_F_timeline(uncontrollable) } : double_Neighbor_FType;

	SYNCHRONIZE double_Channel.double_Channel_timeline {

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

	}

}

