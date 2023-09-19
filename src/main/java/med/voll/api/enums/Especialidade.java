package med.voll.api.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Especialidade {

    @JsonProperty("Ortopedia")
    ORTOPEDIA,
    @JsonProperty("Cardiologia")
    CARDIOLOGIA,
    @JsonProperty("Ginecologia")
    GINECOLOGIA,
    @JsonProperty("Dermatologia")
    DERMATOLOGIA

}
