DOMAIN single_Domain {

	TEMPORAL_MODULE temporal_module = [0, 1000], 500;

	COMP_TYPE SingletonStateVariable single_ChannelType (Channel_L2_F(), Channel_L2_L2(), Idle(), Channel_F_F(), Channel_F_L2()) {

		VALUE Channel_L2_F() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_L2() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Idle() [1, 1000]
		MEETS {
			Channel_L2_F();
			Channel_L2_L2();
			Channel_F_F();
			Channel_F_L2();
		}

		VALUE Channel_F_F() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_F_L2() [20, 35]
		MEETS {
			Idle();
		}

	}

	COMP_TYPE SingletonStateVariable single_Main_ConveyorType (Main_Conveyor_Backward(), Main_Conveyor_Forward(), Main_Conveyor_Still()) {

		VALUE Main_Conveyor_Backward() [1, 1000]
		MEETS {
			Main_Conveyor_Still();
		}

		VALUE Main_Conveyor_Forward() [1, 1000]
		MEETS {
			Main_Conveyor_Still();
		}

		VALUE Main_Conveyor_Still() [1, 1000]
		MEETS {
			Main_Conveyor_Backward();
			Main_Conveyor_Forward();
		}

	}

	COMP_TYPE SingletonStateVariable single_Port_L2Type (Port_L2_Not_Available(), Port_L2_Available()) {

		VALUE Port_L2_Not_Available() [1, 1000]
		MEETS {
			Port_L2_Available();
		}

		VALUE Port_L2_Available() [1, 1000]
		MEETS {
			Port_L2_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable single_Cross_Transfer_2Type (Cross_2_Down(), Cross_2_Up(), Cross_2_Changing()) {

		VALUE Cross_2_Down() [1, 1000]
		MEETS {
			Cross_2_Changing();
		}

		VALUE Cross_2_Up() [1, 1000]
		MEETS {
			Cross_2_Changing();
		}

		VALUE Cross_2_Changing() [3, 3]
		MEETS {
			Cross_2_Down();
			Cross_2_Up();
		}

	}

	COMP_TYPE SingletonStateVariable single_Port_FType (Port_F_Not_Available(), Port_F_Available()) {

		VALUE Port_F_Not_Available() [1, 1000]
		MEETS {
			Port_F_Available();
		}

		VALUE Port_F_Available() [1, 1000]
		MEETS {
			Port_F_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable single_Cross_Conveyor_2Type (Cross_Conveyor_2_Forward(), Cross_Conveyor_2_Backward(), Cross_Conveyor_2_Still()) {

		VALUE Cross_Conveyor_2_Forward() [1, 1000]
		MEETS {
			Cross_Conveyor_2_Still();
		}

		VALUE Cross_Conveyor_2_Backward() [1, 1000]
		MEETS {
			Cross_Conveyor_2_Still();
		}

		VALUE Cross_Conveyor_2_Still() [1, 1000]
		MEETS {
			Cross_Conveyor_2_Forward();
			Cross_Conveyor_2_Backward();
		}

	}

	COMP_TYPE SingletonStateVariable single_Neighbor_FType (Neighbor_F_Available(), Neighbor_F_not_Available()) {

		VALUE Neighbor_F_Available() [1, 1000]
		MEETS {
			Neighbor_F_not_Available();
		}

		VALUE Neighbor_F_not_Available() [1, 1000]
		MEETS {
			Neighbor_F_Available();
		}

	}

	COMP_TYPE SingletonStateVariable single_Neighbor_LType (Neighbor_L_not_Available(), Neighbor_L_Available()) {

		VALUE Neighbor_L_not_Available() [1, 1000]
		MEETS {
			Neighbor_L_Available();
		}

		VALUE Neighbor_L_Available() [1, 1000]
		MEETS {
			Neighbor_L_not_Available();
		}

	}

	COMPONENT single_Channel { FLEXIBLE single_Channel_timeline(trex_internal_dispatch_asap) } : single_ChannelType;
	COMPONENT single_Main_Conveyor { FLEXIBLE single_Main_Conveyor_timeline(trex_internal_dispatch_asap) } : single_Main_ConveyorType;
	COMPONENT single_Port_L2 { FLEXIBLE single_Port_L2_timeline(trex_internal_dispatch_asap) } : single_Port_L2Type;
	COMPONENT single_Cross_Transfer_2 { FLEXIBLE single_Cross_Transfer_2_timeline(trex_internal_dispatch_asap) } : single_Cross_Transfer_2Type;
	COMPONENT single_Port_F { FLEXIBLE single_Port_F_timeline(trex_internal_dispatch_asap) } : single_Port_FType;
	COMPONENT single_Cross_Conveyor_2 { FLEXIBLE single_Cross_Conveyor_2_timeline(trex_internal_dispatch_asap) } : single_Cross_Conveyor_2Type;
	COMPONENT single_Neighbor_F { FLEXIBLE single_Neighbor_F_timeline(uncontrollable) } : single_Neighbor_FType;
	COMPONENT single_Neighbor_L { FLEXIBLE single_Neighbor_L_timeline(uncontrollable) } : single_Neighbor_LType;

	SYNCHRONIZE single_Channel.single_Channel_timeline {

		VALUE Channel_F_F() {

			cd0 single_Main_Conveyor.single_Main_Conveyor_timeline.Main_Conveyor_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 single_Main_Conveyor.single_Main_Conveyor_timeline.Main_Conveyor_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd1;

			cd3 single_Port_F.single_Port_F_timeline.Port_F_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> single_Neighbor_F.single_Neighbor_F_timeline.Neighbor_F_Available();
			DURING [0,+INF] [0,+INF]  cd4;

		}

		VALUE Channel_L2_F() {

			cd0 single_Port_F.single_Port_F_timeline.Port_F_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 single_Main_Conveyor.single_Main_Conveyor_timeline.Main_Conveyor_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd3  <?> single_Neighbor_F.single_Neighbor_F_timeline.Neighbor_F_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> single_Neighbor_L.single_Neighbor_L_timeline.Neighbor_L_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 single_Cross_Transfer_2.single_Cross_Transfer_2_timeline.Cross_2_Up();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 single_Port_L2.single_Port_L2_timeline.Port_L2_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd7 single_Cross_Conveyor_2.single_Cross_Conveyor_2_timeline.Cross_Conveyor_2_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd7;

			cd8 single_Main_Conveyor.single_Main_Conveyor_timeline.Main_Conveyor_Forward();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 single_Cross_Transfer_2.single_Cross_Transfer_2_timeline.Cross_2_Down();
			CONTAINS [0,+INF] [0,+INF]  cd9;

		}

		VALUE Channel_F_L2() {

			cd0  <?> single_Neighbor_L.single_Neighbor_L_timeline.Neighbor_L_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 single_Cross_Transfer_2.single_Cross_Transfer_2_timeline.Cross_2_Up();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 single_Cross_Conveyor_2.single_Cross_Conveyor_2_timeline.Cross_Conveyor_2_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd2;

			cd3 single_Port_L2.single_Port_L2_timeline.Port_L2_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> single_Neighbor_F.single_Neighbor_F_timeline.Neighbor_F_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd4;

			cd5 single_Main_Conveyor.single_Main_Conveyor_timeline.Main_Conveyor_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd5;

			cd6 single_Port_F.single_Port_F_timeline.Port_F_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd6;

			cd8 single_Main_Conveyor.single_Main_Conveyor_timeline.Main_Conveyor_Backward();
			CONTAINS [0,+INF] [0,+INF]  cd8;

			cd9 single_Cross_Transfer_2.single_Cross_Transfer_2_timeline.Cross_2_Down();
			CONTAINS [0,+INF] [0,+INF]  cd9;

		}

		VALUE Channel_L2_L2() {

			cd0 single_Cross_Conveyor_2.single_Cross_Conveyor_2_timeline.Cross_Conveyor_2_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 single_Cross_Conveyor_2.single_Cross_Conveyor_2_timeline.Cross_Conveyor_2_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 single_Cross_Transfer_2.single_Cross_Transfer_2_timeline.Cross_2_Up();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 single_Port_L2.single_Port_L2_timeline.Port_L2_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> single_Neighbor_L.single_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd4;

		}

	}

}

