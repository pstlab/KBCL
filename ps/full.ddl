DOMAIN full_Domain {

	TEMPORAL_MODULE temporal_module = [0, 1000], 500;

	COMP_TYPE SingletonStateVariable full_ChannelType (Channel_F_F(), Idle(), Channel_F_B(), Channel_B_F(), Channel_B_B()) {

		VALUE Channel_F_F() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Idle() [1, 1000]
		MEETS {
			Channel_F_F();
			Channel_F_B();
			Channel_B_F();
			Channel_B_B();
		}

		VALUE Channel_F_B() [30, 60]
		MEETS {
			Idle();
		}

		VALUE Channel_B_F() [30, 60]
		MEETS {
			Idle();
		}

		VALUE Channel_B_B() [10, 20]
		MEETS {
			Idle();
		}

	}

	COMP_TYPE SingletonStateVariable full_Port_BType (Port_B_Not_Available(), Port_B_Available()) {

		VALUE Port_B_Not_Available() [1, 1000]
		MEETS {
			Port_B_Available();
		}

		VALUE Port_B_Available() [1, 1000]
		MEETS {
			Port_B_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable full_Port_FType (Port_F_Not_Available(), Port_F_Available()) {

		VALUE Port_F_Not_Available() [1, 1000]
		MEETS {
			Port_F_Available();
		}

		VALUE Port_F_Available() [1, 1000]
		MEETS {
			Port_F_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable full_Cross_Transfer_3Type (Cross_3_Changing(), Cross_3_Down(), Cross_3_Up()) {

		VALUE Cross_3_Changing() [3, 3]
		MEETS {
			Cross_3_Down();
			Cross_3_Up();
		}

		VALUE Cross_3_Down() [1, 1000]
		MEETS {
			Cross_3_Changing();
		}

		VALUE Cross_3_Up() [1, 1000]
		MEETS {
			Cross_3_Changing();
		}

	}

	COMP_TYPE SingletonStateVariable full_Cross_Transfer_2Type (Cross_2_Changing(), Cross_2_Up(), Cross_2_Down()) {

		VALUE Cross_2_Changing() [3, 3]
		MEETS {
			Cross_2_Up();
			Cross_2_Down();
		}

		VALUE Cross_2_Up() [1, 1000]
		MEETS {
			Cross_2_Changing();
		}

		VALUE Cross_2_Down() [1, 1000]
		MEETS {
			Cross_2_Changing();
		}

	}

	COMP_TYPE SingletonStateVariable full_Main_ConveyorType (Main_Conveyor_Forward(), Main_Conveyor_Backward(), Main_Conveyor_Still()) {

		VALUE Main_Conveyor_Forward() [1, 1000]
		MEETS {
			Main_Conveyor_Still();
		}

		VALUE Main_Conveyor_Backward() [1, 1000]
		MEETS {
			Main_Conveyor_Still();
		}

		VALUE Main_Conveyor_Still() [1, 1000]
		MEETS {
			Main_Conveyor_Forward();
			Main_Conveyor_Backward();
		}

	}

	COMP_TYPE SingletonStateVariable full_Cross_Transfer_1Type (Cross_1_Up(), Cross_1_Down(), Cross_1_Changing()) {

		VALUE Cross_1_Up() [1, 1000]
		MEETS {
			Cross_1_Changing();
		}

		VALUE Cross_1_Down() [1, 1000]
		MEETS {
			Cross_1_Changing();
		}

		VALUE Cross_1_Changing() [3, 3]
		MEETS {
			Cross_1_Up();
			Cross_1_Down();
		}

	}

	COMP_TYPE SingletonStateVariable full_Neighbor_FType (Neighbor_F_not_Available(), Neighbor_F_Available()) {

		VALUE Neighbor_F_not_Available() [1, 1000]
		MEETS {
			Neighbor_F_Available();
		}

		VALUE Neighbor_F_Available() [1, 1000]
		MEETS {
			Neighbor_F_not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable full_Neighbor_BType (Neighbor_B_Available(), Neighbor_B_not_Available()) {

		VALUE Neighbor_B_Available() [1, 1000]
		MEETS {
			Neighbor_B_not_Available();
		}

		VALUE Neighbor_B_not_Available() [1, 1000]
		MEETS {
			Neighbor_B_Available();
		}

	}

	COMPONENT full_Channel { FLEXIBLE full_Channel_timeline(trex_internal_dispatch_asap) } : full_ChannelType;
	COMPONENT full_Port_B { FLEXIBLE full_Port_B_timeline(trex_internal_dispatch_asap) } : full_Port_BType;
	COMPONENT full_Port_F { FLEXIBLE full_Port_F_timeline(trex_internal_dispatch_asap) } : full_Port_FType;
	COMPONENT full_Cross_Transfer_3 { FLEXIBLE full_Cross_Transfer_3_timeline(trex_internal_dispatch_asap) } : full_Cross_Transfer_3Type;
	COMPONENT full_Cross_Transfer_2 { FLEXIBLE full_Cross_Transfer_2_timeline(trex_internal_dispatch_asap) } : full_Cross_Transfer_2Type;
	COMPONENT full_Main_Conveyor { FLEXIBLE full_Main_Conveyor_timeline(trex_internal_dispatch_asap) } : full_Main_ConveyorType;
	COMPONENT full_Cross_Transfer_1 { FLEXIBLE full_Cross_Transfer_1_timeline(trex_internal_dispatch_asap) } : full_Cross_Transfer_1Type;
	COMPONENT full_Neighbor_F { FLEXIBLE full_Neighbor_F_timeline(uncontrollable) } : full_Neighbor_FType;
	COMPONENT full_Neighbor_B { FLEXIBLE full_Neighbor_B_timeline(uncontrollable) } : full_Neighbor_BType;

	SYNCHRONIZE full_Channel.full_Channel_timeline {

		VALUE Channel_B_B() {

			cd0 full_Main_Conveyor.full_Main_Conveyor_timeline.Main_Conveyor_Backward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 full_Main_Conveyor.full_Main_Conveyor_timeline.Main_Conveyor_Forward();
			STARTS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 full_Cross_Transfer_3.full_Cross_Transfer_3_timeline.Cross_3_Down();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 full_Port_B.full_Port_B_timeline.Port_B_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> full_Neighbor_B.full_Neighbor_B_timeline.Neighbor_B_Available();
			DURING [0,+INF] [0,+INF]  cd4;

		}

		VALUE Channel_F_F() {

			cd0 full_Main_Conveyor.full_Main_Conveyor_timeline.Main_Conveyor_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 full_Main_Conveyor.full_Main_Conveyor_timeline.Main_Conveyor_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 full_Cross_Transfer_1.full_Cross_Transfer_1_timeline.Cross_1_Down();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 full_Port_F.full_Port_F_timeline.Port_F_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> full_Neighbor_F.full_Neighbor_F_timeline.Neighbor_F_Available();
			DURING [0,+INF] [0,+INF]  cd4;

		}

		VALUE Channel_F_B() {

			cd0 full_Port_B.full_Port_B_timeline.Port_B_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> full_Neighbor_B.full_Neighbor_B_timeline.Neighbor_B_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 full_Port_F.full_Port_F_timeline.Port_F_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> full_Neighbor_F.full_Neighbor_F_timeline.Neighbor_F_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 full_Main_Conveyor.full_Main_Conveyor_timeline.Main_Conveyor_Backward();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5 full_Cross_Transfer_3.full_Cross_Transfer_3_timeline.Cross_3_Down();
			DURING [0,+INF] [0,+INF]  cd5;

			cd6 full_Cross_Transfer_2.full_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd6;

			cd7 full_Cross_Transfer_1.full_Cross_Transfer_1_timeline.Cross_1_Down();
			DURING [0,+INF] [0,+INF]  cd7;

		}

		VALUE Channel_B_F() {

			cd0 full_Port_F.full_Port_F_timeline.Port_F_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1  <?> full_Neighbor_F.full_Neighbor_F_timeline.Neighbor_F_Available();
			ENDS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 full_Port_B.full_Port_B_timeline.Port_B_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd2;

			cd3  <?> full_Neighbor_B.full_Neighbor_B_timeline.Neighbor_B_Available();
			STARTS-DURING [0,+INF] [0,+INF]  cd3;

			cd4 full_Main_Conveyor.full_Main_Conveyor_timeline.Main_Conveyor_Forward();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5 full_Cross_Transfer_3.full_Cross_Transfer_3_timeline.Cross_3_Down();
			DURING [0,+INF] [0,+INF]  cd5;

			cd6 full_Cross_Transfer_2.full_Cross_Transfer_2_timeline.Cross_2_Down();
			DURING [0,+INF] [0,+INF]  cd6;

			cd7 full_Cross_Transfer_1.full_Cross_Transfer_1_timeline.Cross_1_Down();
			DURING [0,+INF] [0,+INF]  cd7;

		}

	}

}

