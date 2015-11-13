DOMAIN box_Domain {

	TEMPORAL_MODULE temporal_module = [0, 1000], 500;

	COMP_TYPE SingletonStateVariable box_ChannelType (ChannelLL(), Idle(), ChannelBB(), ChannelBF(), ChannelFB(), ChannelLR(), ChannelBL(), ChannelRR(), ChannelRL(), ChannelBR(), ChannelFL(), ChannelFR(), ChannelFF(), ChannelLB(), ChannelRB(), ChannelLF()) {

		VALUE ChannelLL() [20, 35]
		MEETS {
			Idle();
		}

		VALUE Idle() [1, 1000]
		MEETS {
			ChannelLL();
			ChannelBB();
			ChannelBF();
			ChannelFB();
			ChannelLR();
			ChannelBL();
			ChannelRR();
			ChannelRL();
			ChannelBR();
			ChannelFL();
			ChannelFR();
			ChannelFF();
			ChannelLB();
			ChannelRB();
			ChannelLF();
		}

		VALUE ChannelBB() [10, 20]
		MEETS {
			Idle();
		}

		VALUE ChannelBF() [30, 60]
		MEETS {
			Idle();
		}

		VALUE ChannelFB() [30, 60]
		MEETS {
			Idle();
		}

		VALUE ChannelLR() [20, 35]
		MEETS {
			Idle();
		}

		VALUE ChannelBL() [10, 25]
		MEETS {
			Idle();
		}

		VALUE ChannelRR() [20, 35]
		MEETS {
			Idle();
		}

		VALUE ChannelRL() [20, 35]
		MEETS {
			Idle();
		}

		VALUE ChannelBR() [20, 35]
		MEETS {
			Idle();
		}

		VALUE ChannelFL() [10, 25]
		MEETS {
			Idle();
		}

		VALUE ChannelFR() [20, 35]
		MEETS {
			Idle();
		}

		VALUE ChannelFF() [10, 20]
		MEETS {
			Idle();
		}

		VALUE ChannelLB() [20, 35]
		MEETS {
			Idle();
		}

		VALUE ChannelRB() [20, 35]
		MEETS {
			Idle();
		}

		VALUE ChannelLF() [20, 35]
		MEETS {
			Idle();
		}

	}

	COMP_TYPE SingletonStateVariable box_Port_L1Type (Port_L1_Not_Available(), Port_L1_Available()) {

		VALUE Port_L1_Not_Available() [1, 1000]
		MEETS {
			Port_L1_Available();
		}

		VALUE Port_L1_Available() [1, 1000]
		MEETS {
			Port_L1_Not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable box_Port_B1Type (Port_B1_Available(), Port_B1_Not_Available()) {

		VALUE Port_B1_Available() [1, 1000]
		MEETS {
			Port_B1_Not_Available();
		}

		VALUE Port_B1_Not_Available() [1, 1000]
		MEETS {
			Port_B1_Available();
		}

	}

	COMP_TYPE SingletonStateVariable box_Port_F1Type (Port_F1_Available(), Port_F1_Not_Available()) {

		VALUE Port_F1_Available() [1, 1000]
		MEETS {
			Port_F1_Not_Available();
		}

		VALUE Port_F1_Not_Available() [1, 1000]
		MEETS {
			Port_F1_Available();
		}

	}

	COMP_TYPE SingletonStateVariable box_Port_L2Type (Port_L2_Available(), Port_L2_Not_Available()) {

		VALUE Port_L2_Available() [1, 1000]
		MEETS {
			Port_L2_Not_Available();
		}

		VALUE Port_L2_Not_Available() [1, 1000]
		MEETS {
			Port_L2_Available();
		}

	}

	COMP_TYPE SingletonStateVariable box_Port_F2Type (Port_F2_Available(), Port_F2_Not_Available()) {

		VALUE Port_F2_Available() [1, 1000]
		MEETS {
			Port_F2_Not_Available();
		}

		VALUE Port_F2_Not_Available() [1, 1000]
		MEETS {
			Port_F2_Available();
		}

	}

	COMP_TYPE SingletonStateVariable box_Port_R1Type (Port_R1_Available(), Port_R1_Not_Available()) {

		VALUE Port_R1_Available() [1, 1000]
		MEETS {
			Port_R1_Not_Available();
		}

		VALUE Port_R1_Not_Available() [1, 1000]
		MEETS {
			Port_R1_Available();
		}

	}

	COMP_TYPE SingletonStateVariable box_Port_R2Type (Port_R2_Available(), Port_R2_Not_Available()) {

		VALUE Port_R2_Available() [1, 1000]
		MEETS {
			Port_R2_Not_Available();
		}

		VALUE Port_R2_Not_Available() [1, 1000]
		MEETS {
			Port_R2_Available();
		}

	}

	COMP_TYPE SingletonStateVariable box_Neighbor_BType (Neighbor_B_not_Available(), Neighbor_B_Available()) {

		VALUE Neighbor_B_not_Available() [1, 1000]
		MEETS {
			Neighbor_B_Available();
		}

		VALUE Neighbor_B_Available() [1, 1000]
		MEETS {
			Neighbor_B_not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable box_Neighbor_LType (Neighbor_L_not_Available(), Neighbor_L_Available()) {

		VALUE Neighbor_L_not_Available() [1, 1000]
		MEETS {
			Neighbor_L_Available();
		}

		VALUE Neighbor_L_Available() [1, 1000]
		MEETS {
			Neighbor_L_not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable box_Neighbor_RType (Neighbor_R_not_Available(), Neighbor_R_Available()) {

		VALUE Neighbor_R_not_Available() [1, 1000]
		MEETS {
			Neighbor_R_Available();
		}

		VALUE Neighbor_R_Available() [1, 1000]
		MEETS {
			Neighbor_R_not_Available();
		}

	}

	COMP_TYPE SingletonStateVariable box_Neighbor_FType (Neighbor_F_Available(), Neighbor_F_not_Available()) {

		VALUE Neighbor_F_Available() [1, 1000]
		MEETS {
			Neighbor_F_not_Available();
		}

		VALUE Neighbor_F_not_Available() [1, 1000]
		MEETS {
			Neighbor_F_Available();
		}

	}

	COMPONENT box_Channel { FLEXIBLE box_Channel_timeline(trex_internal_dispatch_asap) } : box_ChannelType;
	COMPONENT box_Port_L1 { FLEXIBLE box_Port_L1_timeline(trex_internal_dispatch_asap) } : box_Port_L1Type;
	COMPONENT box_Port_B1 { FLEXIBLE box_Port_B1_timeline(trex_internal_dispatch_asap) } : box_Port_B1Type;
	COMPONENT box_Port_F1 { FLEXIBLE box_Port_F1_timeline(trex_internal_dispatch_asap) } : box_Port_F1Type;
	COMPONENT box_Port_L2 { FLEXIBLE box_Port_L2_timeline(trex_internal_dispatch_asap) } : box_Port_L2Type;
	COMPONENT box_Port_F2 { FLEXIBLE box_Port_F2_timeline(trex_internal_dispatch_asap) } : box_Port_F2Type;
	COMPONENT box_Port_R1 { FLEXIBLE box_Port_R1_timeline(trex_internal_dispatch_asap) } : box_Port_R1Type;
	COMPONENT box_Port_R2 { FLEXIBLE box_Port_R2_timeline(trex_internal_dispatch_asap) } : box_Port_R2Type;
	COMPONENT box_Neighbor_B { FLEXIBLE box_Neighbor_B_timeline(uncontrollable) } : box_Neighbor_BType;
	COMPONENT box_Neighbor_L { FLEXIBLE box_Neighbor_L_timeline(uncontrollable) } : box_Neighbor_LType;
	COMPONENT box_Neighbor_R { FLEXIBLE box_Neighbor_R_timeline(uncontrollable) } : box_Neighbor_RType;
	COMPONENT box_Neighbor_F { FLEXIBLE box_Neighbor_F_timeline(uncontrollable) } : box_Neighbor_FType;

}

