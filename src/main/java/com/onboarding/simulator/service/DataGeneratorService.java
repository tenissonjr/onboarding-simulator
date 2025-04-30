package com.onboarding.simulator.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.onboarding.simulator.model.valid.ValidCaptureItemReport;
import com.onboarding.simulator.model.valid.ValidCapturesReport;
import com.onboarding.simulator.model.valid.ValidLinessesTwoDimensionResult;
import com.onboarding.simulator.model.valid.ValidOcrDocumentReport;
import com.onboarding.simulator.model.valid.ValidOnboardingData;
import com.onboarding.simulator.repository.valid.ValidOnboardingDataRepository;

@Service

public class DataGeneratorService {


    private final ValidOnboardingDataRepository validOnboardingDataRepository;
    private final ImageFactory imageFactory;


    private final Faker faker = new Faker(Locale.forLanguageTag("pt-BR"));

    public DataGeneratorService(ValidOnboardingDataRepository validOnboardingDataRepository, ImageFactory imageFactory) {
        this.validOnboardingDataRepository = validOnboardingDataRepository;
        this.imageFactory = imageFactory;
    }

    public ValidOnboardingData generateValidOnboardingData(){

        ValidOnboardingData data = new ValidOnboardingData();
        data.setCompanyId("199e960e-cdac-462a-b06a-fce9ae09890e");
        data.setScheduleConfigurationId("657e4a6f-0425-4f8f-a44e-57917cccf2f7");
        data.setName("Cadastro");
        data.setEmployee(null);
        data.setEmailClient("");
        data.setPhoneNumber(null);
        data.setScheduleType("Auto ID");
        data.setScheduleStatus("Completed");
        data.setScheduleTime("2025-04-23T15:47:27.41");
        LocalDateTime startTed = LocalDateTime.now();
        Duration duration = Duration.ofSeconds(2); // 2 minutes and 8 seconds
        LocalDateTime completed = startTed.plus(duration);
        data.setDateStarted(startTed);
        data.setDateCompleted(completed);
        data.setDateDuration(duration.toString());
        data.setCapturePlatform("Lite");
        data.setExternalInitialize(false);
        data.setChat(new ArrayList<>());

        // Create and populate capturesReport
        List<ValidCapturesReport> capturesReportList = new ArrayList<>();

        // First CapturesReport
        ValidCapturesReport report1 = new ValidCapturesReport();
        report1.setOid("625be2d0-e465-4200-8e36-097329647357");
        report1.setName("Prova de vida");

        ValidCaptureItemReport itemReportProvaVida = new ValidCaptureItemReport();
        itemReportProvaVida.setOid("a478f091-e58b-4be5-83f8-ca9d803b0a79");
        itemReportProvaVida.setTime("04/23/2025 15:48:36");
        itemReportProvaVida.setType("Png");
        itemReportProvaVida.setUrl("https://certfyremote.blob.core.windows.net/199e960e-cdac-462a-b06a-fce9ae09890e/Face-autoid-3830786e-4062-42b7-adf4-112c8a09e3ff-638810201217711971.jpeg");
        itemReportProvaVida.setUrl_2(null);

        itemReportProvaVida.setOcrDocumentReport(null);
        itemReportProvaVida.setBase64(imageFactory.gerarImagemFace());
        itemReportProvaVida.setBase64_2(null);

        ValidLinessesTwoDimensionResult livenessResult = new ValidLinessesTwoDimensionResult();
        livenessResult.setSuccess(true);
        livenessResult.setResult("");
        livenessResult.setSpoof(false);
        livenessResult.setScore(100);
        livenessResult.setIntegrationType("LivenessPro");
        itemReportProvaVida.setLinessesTwoDimensionResult(livenessResult);


        report1.setCaptureItemReport(List.of(itemReportProvaVida));
        capturesReportList.add(report1);

        // Second CapturesReport
        ValidCapturesReport report2 = new ValidCapturesReport();
        report2.setOid("0dcbf49e-3e88-43a7-a759-6abb72bab841");
        report2.setName(" Documento de Identificação");

        ValidCaptureItemReport itemReportDocumento = new ValidCaptureItemReport();
        itemReportDocumento.setOid("9257c6c7-979b-49dd-a091-ee7ed0080fa4");
        itemReportDocumento.setTime("04/23/2025 15:48:08");
        itemReportDocumento.setType("Png");
        itemReportDocumento.setUrl("https://certfyremote.blob.core.windows.net/199e960e-cdac-462a-b06a-fce9ae09890e/autoid-3830786e-4062-42b7-adf4-112c8a09e3ff-154805.png");
        itemReportDocumento.setUrl_2("");
        itemReportDocumento.setBase64(imageFactory.gerarImagemDocumentoFrente());

        itemReportDocumento.setLinessesTwoDimensionResult(null);

        ValidOcrDocumentReport ocrReport = new ValidOcrDocumentReport();
        ocrReport.setFaceUrl(null);
        ocrReport.setDocType("CNH");
        ocrReport.setDocumentName(faker.name().fullName());
        ocrReport.setRg(generateDocumentNumber());
        ocrReport.setCpf(generateCpf());
        ocrReport.setFiliacao1(faker.name().fullName());
        ocrReport.setFiliacao2(faker.name().fullName());
        ocrReport.setOrgao_emissor_do_RG(generateOrgaoEmissor());
        ocrReport.setEstado_emissor_do_RG(null);
        ocrReport.setData_de_validade(LocalDate.now().plusYears(faker.number().numberBetween(1, 10)));


        LocalDate dataNascimento = faker.date().birthday().toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate();

        

        ocrReport.setData_de_nascimento(dataNascimento);

        ocrReport.setData_primeira_Habilitacao(dataNascimento.plusYears(18));
        ocrReport.setData_de_expedicao(dataNascimento.plusYears(20));
        ocrReport.setLocal_de_emissao_da_CNH(faker.nation().capitalCity() + " " + generateUf());

       

        ocrReport.setNumero_da_CNH(String.valueOf(faker.number().randomNumber(12, true)));
        ocrReport.setCategoria_da_CNH(null);
        ocrReport.setNumero_do_Renach_na_CNH(String.valueOf(faker.number().randomNumber(11, true)));
        ocrReport.setCampo_de_Observacao_na_CNH(null);
        ocrReport.setInformativo_da_observação_da_CNH("Sim");
        ocrReport.setOrgao_emissor_da_CNH(generateOrgaoEmissor());
        ocrReport.setPais(null);

        // (remaining fields are omitted for brevity)
        itemReportDocumento.setOcrDocumentReport(ocrReport);
        itemReportDocumento.setBase64(imageFactory.gerarImagemDocumentoFrente());
        itemReportDocumento.setBase64_2(null);

        report2.setCaptureItemReport(List.of(itemReportDocumento));
        capturesReportList.add(report2);

        data.setCapturesReport(capturesReportList);
        data.setCertificationFacematchRecord(null);
        data.setRelationshipScheduleConfigurationId(null);
        data.setClientGeolocationLatitude("");
        data.setClientGeolocationLongitude("");
        data.setCrSummaryResponse(null);

        return validOnboardingDataRepository.save(data);

    }

    
    private String generateCpf() {
        int[] cpf = new int[11];
        Random random = new Random();
        
        // Generate 9 random digits
        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }
        
        // Calculate first verification digit
        int sum = 0;
        for (int i = 0, j = 10; i < 9; i++, j--) {
            sum += cpf[i] * j;
        }
        int remainder = sum % 11;
        cpf[9] = remainder < 2 ? 0 : 11 - remainder;
        
        // Calculate second verification digit
        sum = 0;
        for (int i = 0, j = 11; i < 10; i++, j--) {
            sum += cpf[i] * j;
        }
        remainder = sum % 11;
        cpf[10] = remainder < 2 ? 0 : 11 - remainder;
        
        // Format CPF (xxx.xxx.xxx-xx)
        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d",
                cpf[0], cpf[1], cpf[2], cpf[3], cpf[4], cpf[5], cpf[6], cpf[7], cpf[8], cpf[9], cpf[10]);
    }
    
    private String generateDocumentNumber() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    
    private String generateOrgaoEmissor() {
        String[] orgaos = {"SSP", "DETRAN", "SESP", "SJS", "SPTC", "IFP", "DGPC"};
        return orgaos[new Random().nextInt(orgaos.length)];
    }
    
    private String generateUf() {
        String[] ufs = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT",
                "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"};
        return ufs[new Random().nextInt(ufs.length)];
    }
    

    
}