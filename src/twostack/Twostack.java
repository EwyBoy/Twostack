package twostack;

import exceptions.TwostackEmptyException;
import exceptions.TwostackFullException;

/**
 * En samling som inneholder to stabler som kalles venstre og høyre, her
 * representert som false (venstre) og true (høyre). Elementene må være av samme
 * type og implementasjonen kan være optimalisert for å jobbe med to ulike
 * stabler slik at det blir avhengigheter i implementasjonen.
 *
 * @author Atle Geitung
 * @param <E> elementtypen
 */
public interface Twostack<E> {

    /**
     * Retunerer hvor mange elementer angitt stabel inneholder.
     *
     * @param right sann hvis høyre stabel og usann hvis venstre stabel
     * @return Størrelsen
     */
    Integer size(Boolean right);

    /**
     * Legger et element i angitt stabel.
     *
     * @param right sann hvis høyre stabel og usann hvis venstre stabel
     * @param element Elementet som skal stables.
     * @throws TwostackFullException når det ikke er plass til elementet.
     */
    void push(Boolean right, E element) throws TwostackFullException;

    /**
     * Stabler av topp-elementet av angitt stabel og returnerer det.
     *
     * @param right sann hvis høyre stabel og usann hvis venstre stabel
     * @return Topp-elementet i samlingen.
     * @throws TwostackEmptyException hvis stabelen er tom
     */
    E pop(Boolean right) throws TwostackEmptyException;

    /**
     * Retunerer topp-elementet av angitt stabel, men fjerner det ikke.
     *
     * @param right sann hvis høyre stabel og usann hvis venstre stabel
     * @return Det første elementet i samlingen.
     * @throws TwostackEmptyException hvis samlingen er tom.
     */
    E peek(Boolean right) throws TwostackEmptyException;

    /**
     * Sjekker om et element er i samlingen.
     *
     * @param element Elementet som kanskje er i samlingen.
     * @return retunerer true hvis elementet er i samlingen.
     */
    Boolean contains(E element);

    /**
     * Fjerner alle elementene fra samlingen.
     */
    void clear();

    /**
     * Retunerer en tabell med alle elementene i samlingen.
     *
     * @param <T> En tabell med samme type som T[].
     * @return En tabell med alle elementene i samlingen.
     */
    <T> T[] toArray(T[] a);

}