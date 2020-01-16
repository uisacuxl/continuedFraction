import static java.lang.System.out;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class continuedFraction {
	public static void help() {
		out.println("Usage: continuedFraction <number>");
		out.println("                         (must include a decimal point)");
		out.println("    or continuedFraction <numerator> <denominator>");
		out.println("                         (must not include a decimal point)");
	}
	public static void main(String[] args) {
		BigInteger numerator,denominator;
		if(args.length==0||args.length>2) {
			help();
			return;
		}
		try {
			if(args.length==1) {
				String s[] = args[0].split("\\.");
				if(s.length!=2) {
					System.out.println("Invalid number");
					help();
					return;
				}
				numerator = new BigInteger(s[0]+s[1]);
				denominator=BigInteger.TEN.pow(s[1].length());
			} else {
				numerator = new BigInteger(args[0]);
				denominator = new BigInteger(args[1]);
			}
		} catch(NumberFormatException e) {
			System.out.println("Invalid number");
			help();
			return;
		}
		BigInteger n[];
		List<BigInteger> a = new ArrayList<>();
		do {
			n=numerator.divideAndRemainder(denominator);
			a.add(n[0]);
			numerator = denominator;
			denominator=n[1];
		} while(!n[1].equals(BigInteger.ZERO));
		for(int i=0;i<a.size()-1;i++) {
			out.print(a.get(i));
			out.print(',');
		}
		out.println(a.get(a.size()-1));
		for(int i=0;i<a.size()-1;i++) {
			out.print(a.get(i));
			out.print("+1/(");
		}
		out.print('\b');
		out.print(a.get(a.size()-1));
		for(int i=0;i<a.size()-3;i++) {
			out.print(')');
		}
		out.println(")");
	}
}