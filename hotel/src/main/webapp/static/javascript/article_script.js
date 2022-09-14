$(document).ready(function()
{
    //espressioni regolari di controllo
    const descriptionRegex = /^[a-z0-9\s]{1,255}$/i;
    const priceRegex = /^\d{1,4}(\.\d{1,2})?$/;

    //evento submit del form
    $('#clientForm').submit((event) =>
    {
        event.preventDefault();
        const description = $('#aDescription').val();
        const price = $('#aPrice').val();
        const formResult = checkData(description, price);
        if(formResult[0] && formResult[1])
            sendData(description,price);
        else
            showErrors(formResult);
    });

    //controllo validità campi
    const checkData = (description, price) =>
    {
        return new Array
        (
            descriptionRegex.test(description),
            priceRegex.test(price)
        );
    }

    //gestione messaggi di errore
    const showErrors = (formResult) =>
    {
        if(!formResult[0])
        {
            $('#descrError').css({'display':'block'});
            return;
        }
        if(!formResult[1])
        {
            $('#priceError').css({'display':'block'});
            return;
        }
    }

	//invio dati 
    const sendData = (description, price) =>
    {
        $.post
	    (
	      'article/saveclient',
	      {
			description:description,
	        price:price
	      },
	      function (response)
	      {
	        if (response === 'save success')
				location.href = '/bravo';
	      }
	    );
    }

    //reset dei messaggi di errore
	$('#aDescription').focusin(function ()
	{
	  $('#descrError').css({'display':'none'});
	});
	$('#aPrice').focusin(function ()
	{
	  $('#priceError').css({'display':'none'});
	});
});