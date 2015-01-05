public class InitLisp {
	Env env;

	InitLisp() {
		env = new Env();
	}
	InitLisp(Env environment) {
		env = environment;
	}

	public Env init() {

		env.put(new Symbol("QUOTE"));

		return env;
	}
}
