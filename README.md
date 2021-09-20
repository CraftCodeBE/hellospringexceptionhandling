# Spring: Mowing repetitive code with an often forgotten annotation

I love coding as long as it's readable. Easier said than done, and in most cases it requires a lot of preparation to end up with clean code. My priority is getting stuff to work and later on in the process I care about appearance. Functionality over beauty, rigth? Well, I learned it's more efficient to both at the same time. Recently I discovered some great tools to speed up the process for clean Java code. 

For this post I will stick to exceptionhandling, but in my next posts I can write about the wonders of Project Lombok and maybe some of you know some kickass tools I could use to expand my toolbox. If so, please leave them in the comment section. 

## @ControllerAdvice && @ExceptionHandler

To handle an exception you would basically write a try/catch in the controller to inform the user of a problem the api has encountered. It works, but in case of many exceptions and when your software grows, it can get crowded in the controllers. For this problem Spring invented a specific way of exceptionhandling. There is a lot to find on this [topic](https://www.baeldung.com/exception-handling-for-rest-with-spring), I use my own approach based on some of these tutorials. 

The @ControlleAdvice allows you to apply global code to a large number of controllers. Oddly, in the controller itself I don't have to do anything, except for maybe erasing all try/catch constructions I want to replace. I created my own class in a different package to handle exceptions. This class is annotated with the @ControllerAdvice and in the class I use a annotated method for every businessexception I want to handle. In my case the MessageBusinessExeption is handled like this: 

    @ExceptionHandler(value = {MessageBusinessException.class})
    protected ResponseEntity<ApiError> handleMessageBusinessException(MessageBusinessException ex) {
        return createErrorResponse(ex);
    }

The createErrorResponse is a helpermethod which returns the correct HttpStatus and response. Status and response are filtered out of the [ApiErrorDictionary](##ApiErrorDictionary) which is basically an unmodifiable map. It uses a model ApiError which in my example only contains a string to respond with a message. The benefit of this model, at least for me: you get a nice json-response.  

## ApiErrorDictionary

This component returns an unmodifiable map which makes it easy to map a response to a status. This way you can use the same practice over and over again. I could even imagine putting the key/value-pairs in a database, altough I haven't tried it. 

    private void fillMap() {
        apiErrorMap.put(ID_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    public Map<String, HttpStatus> getApiErrorMap() {
        return Collections.unmodifiableMap(apiErrorMap);
    }

## MessageBusinessException

There is one last class that needs to be added. The exceptionclass itself, which extends RuntimeException. I tend to write such a businessexceptionclass per feature I implement. 

## Flow 

With this construction in place I can easily integrate new exceptions. I only need to update the BusinessexceptionClass in the feature and add a key/value-pair to the ApiErrorDictionary. Easy peasy, oh so breezy :-). 

## Git

You can find the code on the [github-account]().