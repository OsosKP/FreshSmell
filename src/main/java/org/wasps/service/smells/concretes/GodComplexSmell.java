package org.wasps.service.smells.concretes;

import org.wasps.model.ClassModel;
import org.wasps.model.SmellReportModel;
import org.wasps.service.smells.abstracts.ISmeller;

import java.util.List;
import java.util.stream.Collectors;

public class GodComplexSmell implements ISmeller {
    private final int NUMIMPORTS = 15;

    @Override
    public SmellReportModel smell(ClassModel file) {
        List<String> filteredImports = filterFileImports(file.getImports());
        SmellReportModel reportModel;

        if(filteredImports.size() > NUMIMPORTS){
            reportModel = setReportModel(false, file);
        }
        else{
            reportModel = setReportModel(true, file);
        }

        return reportModel;
    }

    private SmellReportModel setReportModel(boolean pass, ClassModel file){
        SmellReportModel tempReportModel = new SmellReportModel();
        tempReportModel.setSmellName("God Complex");

        if (pass){
            tempReportModel.setScore(100);
            tempReportModel.setDetails("Class: " + file.getName() + " passed the God Complex Smell Test");
        }
        else {
            tempReportModel.setScore(0);
            tempReportModel.setDetails("Class: " + file.getName() + " failed the God Complex Smell Test");
        }
        return tempReportModel;
    }

    //todo filter out the standard imports, we need to see if it is using too many java imports
    private List<String> filterFileImports(List<String> fileImports){
        CharSequence javaLangImports = "java.";
        return fileImports.parallelStream()
                .filter(value -> !value.contains(javaLangImports))
                .collect(Collectors.toList());
    }
}
