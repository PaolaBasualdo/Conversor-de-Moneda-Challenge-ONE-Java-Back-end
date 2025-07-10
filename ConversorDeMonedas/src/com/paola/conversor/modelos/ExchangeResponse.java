package com.paola.conversor.modelos;

import java.util.Map;

public record ExchangeResponse(String base_code, Map<String, Double> conversion_rates) {
}
