package com.onboarding.simulator.dto;


public class SimulatorStatisticsDto {
    private long cadastrosGerados;
    private long notificacoesEncaminhadas;
    private long totalNotificationsFailed;
    private long taxaPorMinutoGeracaoCadastros; // current per minute
    private boolean simulacaoEmProcessamento;
    public SimulatorStatisticsDto(long totalGeneratedCount, long countByStatus, long countByStatus2,
             int cadastrosPerMinute, boolean simulationRunning2) {
        this.cadastrosGerados = totalGeneratedCount;
        this.notificacoesEncaminhadas = countByStatus;
        this.totalNotificationsFailed = countByStatus2;
        this.taxaPorMinutoGeracaoCadastros = cadastrosPerMinute;
        this.simulacaoEmProcessamento = simulationRunning2;

    }
    public long getCadastrosGerados() {
        return cadastrosGerados;
    }
    public void setCadastrosGerados(long totalCadastrosGenerated) {
        this.cadastrosGerados = totalCadastrosGenerated;
    }
    public long getNotificacoesEncaminhadas() {
        return notificacoesEncaminhadas;
    }
    public void setNotificacoesEncaminhadas(long totalNotificationsSent) {
        this.notificacoesEncaminhadas = totalNotificationsSent;
    }
    public long getTotalNotificationsFailed() {
        return totalNotificationsFailed;
    }
    public void setTotalNotificationsFailed(long totalNotificationsFailed) {
        this.totalNotificationsFailed = totalNotificationsFailed;
    }

    public long getTaxaPorMinutoGeracaoCadastros() {
        return taxaPorMinutoGeracaoCadastros;
    }
    public void setTaxaPorMinutoGeracaoCadastros(long generationRate) {
        this.taxaPorMinutoGeracaoCadastros = generationRate;
    }
    public boolean isSimulacaoEmProcessamento() {
        return simulacaoEmProcessamento;
    }
    public void setSimulacaoEmProcessamento(boolean simulationRunning) {
        this.simulacaoEmProcessamento = simulationRunning;
    }

    
}