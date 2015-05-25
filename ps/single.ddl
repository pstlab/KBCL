DOMAIN single_Domain {

	TEMPORAL_MODULE temporal_module = [0, 1000], 500;

	COMP_TYPE SingletonStateVariable single_ChannelType (Channel_R2_L2(), Channel_L2_L2(), Idle(), Channel_R2_R2(), Channel_L2_R2()) {

		VALUE Channel_R2_L2() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_L2() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Idle() [1, 1000]
		MEETS {
			Channel_R2_L2();
			Channel_L2_L2();
			Channel_R2_R2();
			Channel_L2_R2();
		}

		VALUE Channel_R2_R2() [10, 20]
		MEETS {
			Idle();
		}

		VALUE Channel_L2_R2() [10, 20]
		MEETS {
			Idle();
		}

	}

	COMP_TYPE SingletonStateVariable single_Port_R2Type (Port_R2_Not_Available(), Port_R2_Available()) {

		VALUE Port_R2_Not_Available() [1, 1000]
		MEETS {
			Port_R2_Available();
		}

		VALUE Port_R2_Available() [1, 1000]
		MEETS {
			Port_R2_Not_Available();
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

	COMP_TYPE SingletonStateVariable single_Neighbor_RType (Neighbor_R_Available(), Neighbor_R_not_Available()) {

		VALUE Neighbor_R_Available() [1, 1000]
		MEETS {
			Neighbor_R_not_Available();
		}

		VALUE Neighbor_R_not_Available() [1, 1000]
		MEETS {
			Neighbor_R_Available();
		}

	}

	COMPONENT single_Channel { FLEXIBLE single_Channel_timeline(trex_internal_dispatch_asap) } : single_ChannelType;
	COMPONENT single_Port_R2 { FLEXIBLE single_Port_R2_timeline(trex_internal_dispatch_asap) } : single_Port_R2Type;
	COMPONENT single_Port_L2 { FLEXIBLE single_Port_L2_timeline(trex_internal_dispatch_asap) } : single_Port_L2Type;
	COMPONENT single_Cross_Transfer_2 { FLEXIBLE single_Cross_Transfer_2_timeline(trex_internal_dispatch_asap) } : single_Cross_Transfer_2Type;
	COMPONENT single_Cross_Conveyor_2 { FLEXIBLE single_Cross_Conveyor_2_timeline(trex_internal_dispatch_asap) } : single_Cross_Conveyor_2Type;
	COMPONENT single_Neighbor_L { FLEXIBLE single_Neighbor_L_timeline(uncontrollable) } : single_Neighbor_LType;
	COMPONENT single_Neighbor_R { FLEXIBLE single_Neighbor_R_timeline(uncontrollable) } : single_Neighbor_RType;

	SYNCHRONIZE single_Channel.single_Channel_timeline {

		VALUE Channel_L2_R2() {

			cd0 single_Cross_Conveyor_2.single_Cross_Conveyor_2_timeline.Cross_Conveyor_2_Forward();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 single_Cross_Transfer_2.single_Cross_Transfer_2_timeline.Cross_2_Up();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2 single_Port_R2.single_Port_R2_timeline.Port_R2_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 single_Port_L2.single_Port_L2_timeline.Port_L2_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> single_Neighbor_R.single_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5  <?> single_Neighbor_L.single_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd5;

		}

		VALUE Channel_R2_R2() {

			cd0 single_Cross_Conveyor_2.single_Cross_Conveyor_2_timeline.Cross_Conveyor_2_Forward();
			ENDS-DURING [0,+INF] [0,+INF]  cd0;

			cd1 single_Cross_Conveyor_2.single_Cross_Conveyor_2_timeline.Cross_Conveyor_2_Backward();
			STARTS-DURING [0,+INF] [0,+INF]  cd1;

			cd2 single_Cross_Transfer_2.single_Cross_Transfer_2_timeline.Cross_2_Up();
			DURING [0,+INF] [0,+INF]  cd2;

			cd4  <?> single_Neighbor_R.single_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd4;

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

		VALUE Channel_R2_L2() {

			cd0 single_Cross_Conveyor_2.single_Cross_Conveyor_2_timeline.Cross_Conveyor_2_Forward();
			DURING [0,+INF] [0,+INF]  cd0;

			cd1 single_Cross_Transfer_2.single_Cross_Transfer_2_timeline.Cross_2_Up();
			DURING [0,+INF] [0,+INF]  cd1;

			cd2 single_Port_R2.single_Port_R2_timeline.Port_R2_Available();
			DURING [0,+INF] [0,+INF]  cd2;

			cd3 single_Port_L2.single_Port_L2_timeline.Port_L2_Available();
			DURING [0,+INF] [0,+INF]  cd3;

			cd4  <?> single_Neighbor_R.single_Neighbor_R_timeline.Neighbor_R_Available();
			DURING [0,+INF] [0,+INF]  cd4;

			cd5  <?> single_Neighbor_L.single_Neighbor_L_timeline.Neighbor_L_Available();
			DURING [0,+INF] [0,+INF]  cd5;

		}

	}

}

