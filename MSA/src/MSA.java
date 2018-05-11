import java.net.URL; 
import java.util.ArrayList; 
import java.util.List;

import org.biojava.nbio.alignment.Alignments; 
import org.biojava.nbio.core.alignment.template.Profile;
import org.biojava.nbio.core.sequence.ProteinSequence; 
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound; 
import org.biojava.nbio.core.sequence.io.FastaReaderHelper; 
import org.biojava.nbio.core.util.ConcurrencyTools;

public class MSA {
	
   private ArrayList<String> ids;
	
   MSA(ArrayList<String> ids) {
	   this.ids = ids;
   }

   public String multipleSequenceAlignment() throws Exception {  
       List<ProteinSequence> lst = new ArrayList<ProteinSequence>();  
       for (String id : ids) {  
           lst.add(getSequenceForId(id));  
       }  
       Profile<ProteinSequence, AminoAcidCompound> profile = Alignments.getMultipleSequenceAlignment(lst);  
       ConcurrencyTools.shutdown();  
       return profile.toString();
   }

   public ProteinSequence getSequenceForId(String uniProtId) throws Exception {  
       URL uniprotFasta = new URL(String.format("http://www.uniprot.org/uniprot/%s.fasta", uniProtId));  
       ProteinSequence seq = FastaReaderHelper.readFastaProteinSequence(uniprotFasta.openStream()).get(uniProtId);  
       //System.out.printf("id : %s %s%n%s%n", uniProtId, seq, seq.getOriginalHeader());  
       return seq;  
   }

}

