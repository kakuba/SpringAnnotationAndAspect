package pl.spring.demo.service;

import static java.lang.String.format;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import pl.spring.demo.to.AthorTo;
import pl.spring.demo.to.BookEntity;


/**
 * {@link BookEntity} specific assertions - Generated by CustomAssertionGenerator.
 */
public class BookEntityAssert extends AbstractAssert<BookEntityAssert, BookEntity> {

  /**
   * Creates a new </code>{@link BookEntityAssert}</code> to make assertions on actual BookEntity.
   * @param actual the BookEntity we want to make assertions on.
   */
  public BookEntityAssert(BookEntity actual) {
    super(actual, BookEntityAssert.class);
  }

  /**
   * An entry point for BookEntityAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
   * With a static import, one's can write directly : <code>assertThat(myBookEntity)</code> and get specific assertion with code completion.
   * @param actual the BookEntity we want to make assertions on.
   * @return a new </code>{@link BookEntityAssert}</code>
   */
  public static BookEntityAssert assertThat(BookEntity actual) {
    return new BookEntityAssert(actual);
  }

  /**
   * Verifies that the actual BookEntity's authors contains the given AthorTo elements.
   * @param authors the given elements that should be contained in actual BookEntity's authors.
   * @return this assertion object.
   * @throws AssertionError if the actual BookEntity's authors does not contain all given AthorTo elements.
   */
  public BookEntityAssert hasAuthors(AthorTo... authors) {  
    // check that actual BookEntity we want to make assertions on is not null.
    isNotNull();

    // check that given AthorTo varargs is not null.
    if (authors == null) throw new AssertionError("Expecting authors parameter not to be null.");
    
    // check with standard error message (see commented below to set your own message).
    Assertions.assertThat(actual.getAuthors()).contains(authors);

    // uncomment the 4 lines below if you want to build your own error message :
    // WritableAssertionInfo assertionInfo = new WritableAssertionInfo();
    // String errorMessage = "my error message";
    // assertionInfo.overridingErrorMessage(errorMessage);
    // Iterables.instance().assertContains(assertionInfo, actual.getTeamMates(), teamMates);
    
    // return the current assertion for method chaining
    return this;
  }

  /**
   * Verifies that the actual BookEntity has no authors.
   * @return this assertion object.
   * @throws AssertionError if the actual BookEntity's authors is not empty.
   */
  public BookEntityAssert hasNoAuthors() {  
    // check that actual BookEntity we want to make assertions on is not null.
    isNotNull();

    // we overrides the default error message with a more explicit one
    String errorMessage = format("\nExpected :\n  <%s>\nnot to have authors but had :\n  <%s>", actual, actual.getAuthors());
    
    // check
    if (!actual.getAuthors().isEmpty()) throw new AssertionError(errorMessage);
    
    // return the current assertion for method chaining
    return this;
  }
  

  /**
   * Verifies that the actual BookEntity's id is equal to the given one.
   * @param id the given id to compare the actual BookEntity's id to.
   * @return this assertion object.
   * @throws AssertionError - if the actual BookEntity's id is not equal to the given one.
   */
  public BookEntityAssert hasId(Long id) {
    // check that actual BookEntity we want to make assertions on is not null.
    isNotNull();

    // we overrides the default error message with a more explicit one
    String errorMessage = format("\nExpected <%s> id to be:\n  <%s>\n but was:\n  <%s>", actual, id, actual.getId());
    
    // check
    if (!actual.getId().equals(id)) { throw new AssertionError(errorMessage); }

    // return the current assertion for method chaining
    return this;
  }

  /**
   * Verifies that the actual BookEntity's title is equal to the given one.
   * @param title the given title to compare the actual BookEntity's title to.
   * @return this assertion object.
   * @throws AssertionError - if the actual BookEntity's title is not equal to the given one.
   */
  public BookEntityAssert hasTitle(String title) {
    // check that actual BookEntity we want to make assertions on is not null.
    isNotNull();

    // we overrides the default error message with a more explicit one
    String errorMessage = format("\nExpected <%s> title to be:\n  <%s>\n but was:\n  <%s>", actual, title, actual.getTitle());
    
    // check
    if (!actual.getTitle().equals(title)) { throw new AssertionError(errorMessage); }

    // return the current assertion for method chaining
    return this;
  }

}
