package org.wasps.service.smells.concretes;

import org.wasps.model.ClassModel;
import org.wasps.model.SmellReportModel;
import org.wasps.service.smells.abstracts.ISmeller;

import java.util.List;
import java.util.stream.Collectors;

//Class which has too much functionality, ie a class which does too much
public class GodComplexSmell extends SmellerBase implements ISmeller {
    private final int NUMIMPORTS = 15;

    public GodComplexSmell(int id, String name) {
        super(id, name);
    }

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

    private List<String> filterFileImports(List<String> fileImports){
        CharSequence javaLangImports = "java.";
        CharSequence jUnitImports = "junit";
        return fileImports.parallelStream()
                .filter(value -> !value.contains(javaLangImports) && !value.contains(jUnitImports))
                .collect(Collectors.toList());
    }
}
