import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wasps.configuration.MappingProfile;
import org.wasps.data.repository.concretes.ParsedRepository;
import org.wasps.model.ParsedClass;
import org.wasps.model.SmellReportModel;
import org.wasps.service.concretes.ParsingService;
import org.wasps.service.smells.concretes.AbstractClassMethodsSmell;

import java.util.ArrayList;
import java.util.List;

public class AbstractClassMethodsSmellTests {
    ParsedRepository repo;
    List<ParsedClass> files;
    SmellReportModel tempModel;

    @Before
    public void setup() {
        repo = new ParsedRepository();
        files = new ArrayList<>();
    }

    @Test
    public void AbstractClassMethodSmellTestTrue(){
        AbstractClassMethodsSmell tempSmell = new AbstractClassMethodsSmell(1, "");
        ParsingService tempService = new ParsingService();
        MappingProfile tempMappingProfile = new MappingProfile();

        try{
            files = tempService.parse("src/tests/test_data");
            repo.insert(files);
        }
        catch (Exception e){
            Assert.fail(e.toString());
        }

        tempModel = tempSmell.smell(tempMappingProfile.map(repo.get("RandomAbstractClass")).get(0));
        Assert.assertEquals(100, (int) tempModel.getScore());
    }

    @Test
    public void AbstractClassMethodSmellTestFalse(){
        AbstractClassMethodsSmell tempSmell = new AbstractClassMethodsSmell(1, "");
        ParsingService tempService = new ParsingService();
        MappingProfile tempMappingProfile = new MappingProfile();

        try{
            files = tempService.parse("src/tests/test_data");
            repo.insert(files);
        }
        catch (Exception e){
            Assert.fail(e.toString());
        }

        tempModel = tempSmell.smell(tempMappingProfile.map(repo.get("RandomAbstractClass1")).get(0));
        Assert.assertEquals(0, (int) tempModel.getScore());
    }

    @Test
    public void AbstractClassMethodSmellTestFalseNonAbstract(){
        AbstractClassMethodsSmell tempSmell = new AbstractClassMethodsSmell(1, "");
        ParsingService tempService = new ParsingService();
        MappingProfile tempMappingProfile = new MappingProfile();

        try{
            files = tempService.parse("src/tests/test_data");
            repo.insert(files);
        }
        catch (Exception e){
            Assert.fail(e.toString());
        }

        tempModel = tempSmell.smell(tempMappingProfile.map(repo.get("RandomClass")).get(0));
        Assert.assertEquals(100, (int) tempModel.getScore());
    }
}
