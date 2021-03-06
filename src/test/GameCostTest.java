package test;

import game.CostMCT;
import game.CostMET;
import game.CostMaxMin;
import game.CostMinMin;
import game.CostOLB;
import game.CostSufferage;
import game.GameCost;
import game.GameQuick;
import game.MCT;
import game.MET;
import game.MaxMin;
import game.MinMin;
import game.OLB;
import game.Sufferage;

public class GameCostTest {
	public GameCost test1() {
		
		GameCost wo = new GameCost(2,2);
		wo.setBPrint(true);
		
		int[] iaLength = {1000,1000};
		wo.setIaTask(iaLength);

		int[] iaCPU = {8,8};
		wo.setIaCPU(iaCPU);
		
		double[] daPrice = {1,1};
		wo.setDaPrice(daPrice);

		wo.setDDeadline(150);
		
		double[][] dmPrediction = {{1,2},{1,1.2}};
		wo.setDmPrediction(dmPrediction);

//		wo.schedule();
		return wo;
	}

	public GameCost test2() {
		
		GameCost wo = new GameCost(3,3);
		wo.setBPrint(true);
		
		int[] iaLength = {10000,10000,10000};
		wo.setIaTask(iaLength);

		int[] iaCPU = {24,24,24};
		wo.setIaCPU(iaCPU);
		
		double[] daPrice = {1,1.2,1.5};
		wo.setDaPrice(daPrice);

		wo.setDDeadline(1000);
		
		double[][] dmPrediction = {
				{1,1.2,1.3},
				{1,1.1,1.2},
				{1,1.3,1.5},
				};
		wo.setDmPrediction(dmPrediction);

		return wo;
	}
	
	public GameCost test21() {
		
		GameCost wo = new GameCost(3,3);
		wo.setBPrint(true);
		
		int[] iaLength = {10000,10000,10000};
		wo.setIaTask(iaLength);

		int[] iaCPU = {24,24,24};
		wo.setIaCPU(iaCPU);
		
		double[] daPrice = {2,1.5,1};
		wo.setDaPrice(daPrice);

//		wo.setDDeadline(1000);
		
		double[][] dmPrediction = {
				{1,1.2,1.3},
				{1.0,1.5,2.0},
				{1.2,1.3,1.4},
				};
		wo.setDmPrediction(dmPrediction);

		return wo;
	}
        
        public GameCost test22() {
		
		GameCost wo = new GameCost(3,3);
		wo.setBPrint(true);
		
		int[] iaLength = {10000,10000,10000};
		wo.setIaTask(iaLength);

		int[] iaCPU = {24,24,24};
		wo.setIaCPU(iaCPU);
		
		double[] daPrice = {1.2,1.1,1};
		wo.setDaPrice(daPrice);

//		wo.setDDeadline(1000);
		
		double[][] dmPrediction = {
				{1,1.2,1.3},
				{1.0,1.5,2.0},
				{1.2,1.3,1.4},
				};
		wo.setDmPrediction(dmPrediction);

		return wo;
	}


	public GameCost test3() {
		int heteroMachine = 10;
		int heteroPrice = 10;
		int heteroTask = 10;
		
		int iClass=10, iSite = 10;
		
		GameCost wo = new GameCost(iClass,iSite);
		wo.setBPrint(false);

//		wo.setDDeadline(18000);
		
		int[] iaLength = new int[iClass];
		int totalAct = 0;
		for (int j = 0; j < iClass; j++) {
			iaLength[j] = 10000 + Math.round(Math.round(heteroTask*10000 * Math.random()));
			totalAct += iaLength[j];
		}
		wo.setIaTask(iaLength);
		System.out.println("Total #Activity = " + totalAct);

		double[] iaSpeedCPU = new double[iSite];
		double[] daPrice = new double[iSite];
		int[] iaCPU = new int[iSite];
		int totalCPU = 0;
		for (int j = 0; j < iSite; j++) {
			iaCPU[j] = 16 + (int) (Math.random() * 16 * heteroMachine);
			totalCPU += iaCPU[j];
			iaSpeedCPU[j] = Math.random() * heteroMachine + 1;
			daPrice[j] = (1 + Math.round( heteroPrice*Math.random() ) ) / iaSpeedCPU[j];
		}
		wo.setDaPrice(daPrice);
		wo.setIaCPU(iaCPU);
		System.out.println("Total #CPU = " + totalCPU);
		
		double tmpPrediciton;
		double[][] dmPrediction = new double[iClass][iSite];
		for (int i = 0; i < iClass; i++) {
			tmpPrediciton = 1 + Math.random() * heteroTask;
			for (int j = 0; j < iSite; j++) {
				dmPrediction[i][j] = tmpPrediciton * iaSpeedCPU[j] * (Math.random() + 0.5);
			}
		}
		wo.setDmPrediction(dmPrediction);
		return wo;
	}

	void testFinal() {
            double deadline =300;
            GameCostTest gct = new GameCostTest();
			GameCost wo = gct.test22();
            
            for (int s = 1; s < 100; s++) {
            	deadline += 10;
			
//			System.out.println("----------------COST OPTIMIZATION--------------");
//          long tw1 = System.currentTimeMillis();
            wo.setDeadline(deadline);
            wo.setBPrint(false);
            wo.schedule();
//			System.out.println("Cost      = " + wo.getDCost());
//			System.out.println("Time      = " + wo.getDTime());
//			System.out.println("AlgExeTime= "+ (System.currentTimeMillis() - tw1));
//			System.out.println("Makespan% = " + wo.getDFinalMakespan() / wo.getDDeadline() * 100);
//			System.out.println();

//			System.out.println("----------------QUICK OPTIMIZATION--------------");
//			GameQuick opt = new GameQuick(wo.getIClass(), wo.getISite());
//			opt.init(wo);
//			long tw8 = System.currentTimeMillis();
//			opt.schedule();
//			System.out.println("Cost%     = " + opt.getDCost() / wo.getDCost() * 100);
//			System.out.println("Cost     = " + opt.getDCost() );
//			System.out.println("Time%     = " + opt.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + opt.getDFinalMakespan()	/ wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tw8));
//			System.out.println();

//			System.out.println("----------------OLB--------------");
//			OLB olb = new OLB(wo.getIClass(), wo.getISite());
//			olb.init(wo);
//			long tolb = System.currentTimeMillis();
//			olb.olbStart();
//			System.out.println("Cost%     = " + olb.getDCost() / wo.getDCost() * 100); 
//			System.out.println("Cost      = " + olb.getDCost() );
//			System.out.println("Time%     = " + olb.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + olb.getDFinalMakespan() / wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tolb));
//			System.out.println();

//			System.out.println("----------------OLB(Cost)--------------");
			CostOLB colb = new CostOLB(wo.getIClass(), wo.getISite());
			colb.init(wo);
			long tw2 = System.currentTimeMillis();
			colb.minOLBCost();
//			System.out.println("Cost%     = " + colb.getDCost() / wo.getDCost() * 100);
//          System.out.println("Cost      = " + colb.getDCost() );
//			System.out.println("Time%     = " + colb.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + colb.getDFinalMakespan() / wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tw2));
//			System.out.println();

//			System.out.println("----------------MCT--------------");
//			MCT mct = new MCT(wo.getIClass(), wo.getISite());
//			mct.init(wo);
//			long tmct = System.currentTimeMillis();
//			mct.minct();
//			System.out.println("Cost%     = " + mct.getDCost() / wo.getDCost() * 100);
//			System.out.println("Cost     = " + mct.getDCost() );
//			System.out.println("Time%     = " + mct.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + mct.getDFinalMakespan() / wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tmct));
//			System.out.println();

//			System.out.println("----------------MCT(Cost)--------------");
			CostMCT cmct = new CostMCT(wo.getIClass(), wo.getISite());
			cmct.init(wo);
			long tw4 = System.currentTimeMillis();
			cmct.minCTCost();
//			System.out.println("Cost%     = " + cmct.getDCost() / wo.getDCost() * 100);
//                        System.out.println("Cost     = " + cmct.getDCost() );
//			System.out.println("Time%     = " + cmct.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + cmct.getDFinalMakespan() / wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tw4));
//			System.out.println();

//			System.out.println("----------------MinMin--------------");
//			MinMin minmin = new MinMin(wo.getIClass(), wo.getISite());
//			minmin.init(wo);
//			long tw9 = System.currentTimeMillis();
//			minmin.schedule();
//			System.out.println("Cost%     = " + minmin.getDCost() / wo.getDCost() * 100);
//          System.out.println("Cost     = " + minmin.getDCost() );
//			System.out.println("Time%     = " + minmin.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + minmin.getDFinalMakespan() / wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tw9));
//			System.out.println();

//			System.out.println("----------------MinMin(Cost)--------------");
			CostMinMin minminc = new CostMinMin(wo.getIClass(), wo.getISite());
			minminc.init(wo);
			long tw3 = System.currentTimeMillis();
			minminc.minMinCost();
//			System.out.println("Cost%     = " + minminc.getDCost() / wo.getDCost() * 100);
//          System.out.println("Cost     = " + minminc.getDCost() );
//			System.out.println("Time%     = " + minminc.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + minminc.getDFinalMakespan() / wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tw3));
//			System.out.println();

//			System.out.println("----------------MaxMin--------------");
//			MaxMin max = new MaxMin(wo.getIClass(), wo.getISite());
//			max.init(wo);
//			long tmax = System.currentTimeMillis();
//			max.maxmin();
//			System.out.println("Cost%     = " + max.getDCost() / wo.getDCost() * 100);
//          System.out.println("Cost     = " + max.getDCost() );
//			System.out.println("Time%     = " + max.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + max.getDFinalMakespan() / wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tmax));
//			System.out.println();

//			System.out.println("----------------MaxMin(Cost)--------------");
			CostMaxMin mat = new CostMaxMin(wo.getIClass(), wo.getISite());
			mat.init(wo);
			long tw5 = System.currentTimeMillis();
			mat.maxmin();
//			System.out.println("Cost%     = " + mat.getDCost() / wo.getDCost() * 100);
//          System.out.println("Cost     = " + mat.getDCost() );
//			System.out.println("Time%     = " + mat.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + mat.getDFinalMakespan() / wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tw5));
//			System.out.println();
//
//			System.out.println("----------------Sufferage--------------");
//			Sufferage suff = new Sufferage(wo.getIClass(), wo.getISite());
//			suff.init(wo);
//			long tsuff = System.currentTimeMillis();
//			suff.minSufferage();
//			System.out.println("Cost%     = " + suff.getDCost() / wo.getDCost() * 100);
//          System.out.println("Cost     = " + suff.getDCost() );
//			System.out.println("Time%     = " + suff.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + suff.getDFinalMakespan() / wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tsuff));
//			System.out.println();

//			System.out.println("----------------Sufferage(Cost)--------------");
			CostSufferage minsuff = new CostSufferage(wo.getIClass(), wo.getISite());
			minsuff.init(wo);
			long tw6 = System.currentTimeMillis();
			minsuff.minSufferage();
//			System.out.println("Cost%     = " + minsuff.getDCost() / wo.getDCost() * 100);
//          System.out.println("Cost     = " + minsuff.getDCost() );
//			System.out.println("Time%     = " + minsuff.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + minsuff.getDFinalMakespan() / wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tw6));
//			System.out.println();

//			System.out.println("----------------MET--------------");
//			MET met = new MET(wo.getIClass(), wo.getISite());
//			met.init(wo);
//			long tmet = System.currentTimeMillis();
//			met.minet();
//			System.out.println("Cost%     = " + met.getDCost() / wo.getDCost() * 100);
//          System.out.println("Cost     = " + met.getDCost() );
//			System.out.println("Time%     = " + met.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + met.getDFinalMakespan() / wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tmet));
//			System.out.println();

//			System.out.println("----------------MET(Cost)--------------");
			CostMET cmet = new CostMET(wo.getIClass(), wo.getISite());
			cmet.init(wo);
			long tw7 = System.currentTimeMillis();
			cmet.minETCost();
//			System.out.println("Cost%     = " + cmet.getDCost() / wo.getDCost() * 100);
//          System.out.println("Cost     = " + cmet.getDCost() );
//			System.out.println("Time%     = " + cmet.getDTime() / wo.getDTime() * 100);
//			System.out.println("Makespan% = " + cmet.getDFinalMakespan() / wo.getDFinalMakespan() * 100);
//			System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tw7));
//			System.out.println();
                        
//          System.out.println("==================="+s+"=============");
			
            System.out.println( deadline+ " "+ wo.getDCost() + " " + colb.getDCost() + " "+ cmct.getDCost() + " "+ 
            		minminc.getDCost() + " "+ mat.getDCost() + " "+ minsuff.getDCost() + " " + cmet.getDCost() );
//            System.out.println( deadline+ " "+ wo.getdSystemEfficiency() + " " + colb.getdSystemEfficiency() + " "+ cmct.getdSystemEfficiency() + " "+ 
//            		minminc.getdSystemEfficiency() + " "+ mat.getdSystemEfficiency() + " "+ minsuff.getdSystemEfficiency() + " " + cmet.getdSystemEfficiency() );
//            System.out.println( deadline+ " "+ wo.getdSystemCostEfficiency() + " " + colb.getdSystemCostEfficiency() + " "+ cmct.getdSystemCostEfficiency() + " "+ 
//            		minminc.getdSystemCostEfficiency() + " "+ mat.getdSystemCostEfficiency() + " "+ minsuff.getdSystemCostEfficiency() + " " + cmet.getdSystemCostEfficiency() );

        }
        System.out.println( "# deadline  gamecost olb mct minmin maxmin sufferage met" );

	}

	void testCvg() {
		GameCostTest gct = new GameCostTest();
		GameCost wo = gct.test3();
		long tw1 = System.currentTimeMillis();
		wo.setBPrint(false);
		wo.schedule();
		System.out.println("----------------COST OPTIMIZATION--------------");
		System.out.println("Cost      = " + wo.getDCost());
		System.out.println("Time      = " + wo.getDTime());
		System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tw1));
		System.out.println("Makespan% = " + wo.getDFinalMakespan() / wo.getDDeadline() * 100);

		System.out.println("----------------WORKFLOW OPTIMIZATION--------------");
		GameQuick opt = new GameQuick(wo.getIClass(), wo.getISite());
		opt.init(wo);
		opt.setBPrint(false);
		long tw8 = System.currentTimeMillis();
		opt.schedule();
		System.out.println("Cost%     = " + opt.getDCost() / wo.getDCost() * 100);		
                System.out.println("Cost     = " + opt.getDCost());
		System.out.println("Time%     = " + opt.getDTime() / wo.getDTime() * 100);
		System.out.println("Makespan% = " + opt.getDFinalMakespan()
				/ wo.getDFinalMakespan() * 100);
		System.out.println("AlgExeTime= " + (System.currentTimeMillis() - tw8));
		System.out.println();

		for (int i = 0; i < wo.getVCost().size(); i++) {
			System.out.println((i + 1) + " " + wo.getVCost().get(i));
		}

	}

	public static void main(String[] args) {
		GameCostTest co = new GameCostTest();
		co.testFinal();
		
	}
}
