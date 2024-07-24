var result = holdings.stream()
    .filter(holding -> holding.getNxSecurityType() != null
        && (Constants.EQUITY_NX_SECURITY_TYPES.contains(holding.getNxSecurityType())
            || (Constants.MUTUAL_FUNDS.contains(holding.getNxSecurityType())
                && holding.getBloombergSecurityType() != null
                && Constants.CLOSED_END_FUND.contains(holding.getBloombergSecurityType()))))
    .collect(Collectors.toList()); // Or any other terminal operation
