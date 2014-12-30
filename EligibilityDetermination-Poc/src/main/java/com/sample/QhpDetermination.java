package com.sample;

import org.drools.core.event.DebugAgendaEventListener;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;

/**
 * This is a sample class to launch a rule.
 */
public class QhpDetermination {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            
            Applicant applicant=new Applicant();
            applicant.setAge(27);
            applicant.setIncome(5000);
            applicant.setFamilySize(2);
            
            int fplCalculate;
            
            fplCalculate=300;
            applicant.setFPL(fplCalculate);
            
            KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( kSession, "./QhpDetermination", 1000 );

            
            kSession.insert(applicant);
            kSession.fireAllRules();
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

     
    public static class Applicant {


        private int income;
        private int familySize;
        private int age;
        private int FPL;
        
		public int getIncome() {
			return income;
		}
		public void setIncome(int income) {
			this.income = income;
		}
		public int getFamilySize() {
			return familySize;
		}
		public void setFamilySize(int familySize) {
			this.familySize = familySize;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public int getFPL() {
			return FPL;
		}
		public void setFPL(int fPL) {
			FPL = fPL;
		}

    }

}
