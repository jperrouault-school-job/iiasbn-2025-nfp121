package fr.formation.chainofresp;

import fr.formation.http.HttpRequest;
import fr.formation.http.HttpResponse;

public class AbstractFilter {
    protected AbstractFilter next = null;

	public void doChain(HttpRequest request, HttpResponse response) {
		if (this.next != null) {
			this.next.doChain(request, response);
		}
	}
	
	public AbstractFilter chain(AbstractFilter hdlr) {
		this.next = hdlr;

		return hdlr;
	}
}
