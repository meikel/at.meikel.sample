package aspects;

public aspect TracingAspect {

	private void log(String msg) {
		System.out.println(msg);
	}

	pointcut anyStaticMethodOfPrimeNumberCalculator() : call(static * logic.PrimeNumberCalculator.*(..)) ;

	before() : anyStaticMethodOfPrimeNumberCalculator() {
		StringBuilder sb = new StringBuilder("Entering ");
		sb.append(thisJoinPointStaticPart.getSignature().toLongString());
		sb.append(" | ");
		boolean first = true;
		for (Object o : thisJoinPoint.getArgs()) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append(o == null ? "null" : o.toString());
		}
		log(sb.toString());
	};

	after() returning(Object result) : anyStaticMethodOfPrimeNumberCalculator() {
		StringBuilder sb = new StringBuilder("Exiting ");
		sb.append(thisJoinPointStaticPart.getSignature().toLongString());
		sb.append(" | ");
		boolean first = true;
		for (Object o : thisJoinPoint.getArgs()) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append(o == null ? "null" : o.toString());
		}
		sb.append(" --> ");
		sb.append(result == null ? "null" : result.toString());
		log(sb.toString());
	};
}
