package humankind.tools;

public class TransactionParser {
	private TransactionContext context;
	
	public TransactionParser(TransactionContext parserContext) {
		this.context = parserContext;
	}

	public TransactionContext getParserContext() {
		return context;
	}

	public IntergalacticTransaction parseTransaction(String txString) {
		String[] parts = txString.split(" ");
		if (parts.length == 3) {
			return new IntergalacticTransaction() {
				@Override
				public void run() {
					context.getVocabulary().put(parts[0], parts[2]);
				}
			};
		} else if (parts[parts.length - 1].equals("?")) {
			return new IntergalacticPriceConversionTx(parts, context);
		} else {
			return new PricePerUnitDefinitionTx(parts, context);
		}
	}
}
