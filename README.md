# Spring: Mowing repetitive code with an often forgotten annotation

I like code as long as it's readable. So my resolution for the New Year, clean code. Easier said than done, and in most cases it requires a lot of preparation to end up with clean code. My priority is getting stuff to work and later on in the process I care about appearance. Functionality over beauty, rigth? Well, I learned it's more efficient to do both at the same time. Recently I discovered some great tools to speed up the process for clean Java code.

For this post I will stick to exceptionhandling, but in my next posts the wonders of custom Builders will follow and maybe some of you know some kickass tools I could use to expand my toolbox. If so, feel free to leave them in the comment section.

<h2>Removing repetition</h2>
To handle an exception you would basically write a try/catch in the controller to inform the user of a problem the api has encountered. It works, but in case of many exceptions and when your software grows, it can get crowded in the controllers. The following code shows a basic implementation of exception handling. In case something goes wrong during the call for all messages, you'll receive a response for which the Spring Framework already has a mechanism in place. You could use the standard message by returning e.getMessage(), as done below, or you could write your own message. The important part to notice is that in this approach you would have to write a similar catch for every endpoint.

[img]

As mentioned before, in large appplications with lot of controllers and CRUD-operations you would put a lot of time in repetitive code. For this problem Spring invented a more cryptic way of exceptionhandling. There is a lot to find on this <a href="https://www.baeldung.com/exception-handling-for-rest-with-spring">topic</a>, but I use my own approach based on some of these tutorials. Of course there can be optimizations, so feel free to make my effort even more practical. 

<h2>ExceptionHandler</h2>

The first thing I do, is write class that handles exceptions. Apart from a constructor that initializes a dictionary, this class will handle for instance the MessageBusinessException. Of course other exceptions can be integrated as well. The important part to notice is the annotation @ControllerAdvice. The @ControlleAdvice allows you to apply global code to a large number of controllers. It will handle exceptions across the whole application in one global handling component and returns an error response based on the given businessexception. In this case only a MessageBusinessException, but for more complex applications other exceptions can be written. In the controller itself you don't have to do anything, except for maybe erasing all try/catch-constructions I want to replace.

<img src="https://craftcode.be/wp-content/uploads/2022/01/exception_handling_4.png" alt="ControllerAdvice">

<img src="https://craftcode.be/wp-content/uploads/2022/01/exception_handling_1.png" alt="ExceptionHandler">

For the creation of an errormessage I've written a helper-method. The createErrorResponse is a helpermethod which returns the HttpStatus and response. Status and response are filtered out of the <a href="##Dictionary">ApiErrorDictionary</a> which is basically an unmodifiable map. It uses a model ApiError which in my example only contains a string to respond with a message. The benefit of this model is standardizing responses which can be the same in a lot of controllers. Instead of using an unmodifiable map, I could put the the key-value pairs in a database. Again, the idea is to mow all repetitive lines of code, which for complicated apps can add up. 

<img src="https://craftcode.be/wp-content/uploads/2022/01/exception_handling_2.png" alt="Unmodifiable Map">

<h2>MessageBusinessException</h2>

There is one last class that needs to be added. An exception is often thrown in the service and then it goes up, through the controller, to the user. In order to throw custom exceptions, you'll need to write a custom class which extends RuntimeException. I tend to write such a businessexceptionclass per feature I implement. In my democode it's part of the servicepackage, but it could be another choice to structure your code as package by feature and declare a businessexceptionclass per feature. Anyhow, the MessageBusinessException would look like this.

<img src="https://craftcode.be/wp-content/uploads/2022/01/exception_handling_3.png" alt="BusinessException">

<h2>Flow</h2>

With this construction in place I can easily integrate new exceptions. I only need to update the BusinessexceptionClass in the feature, add a key/value-pair to the ApiErrorDictionary and update the according service by throwing the exception. Easy peasy, oh so breezy :-).

<h2>Git</h2>

This tutorial might be a bit short, but you can the code on the <a href="https://github.com/CraftCodeBE/hellospringexceptionhandling">github-account</a>.