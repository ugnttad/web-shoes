document.addEventListener('DOMContentLoaded', () => {
    const cardsPerPage = 8;
    const cards = document.querySelectorAll('.card');
    
    const totalPages = Math.ceil(cards.length / cardsPerPage);


    let listPageHTML = '';
    for (let index = 1; index <= totalPages; index++){
        listPageHTML+= `
            <li class="page-item"><a class="page-link" href="#" data-page="${index}">${index}</a></li>
        `
        console.log(typeof String(index));
    }
    document.querySelector('.list-page').innerHTML = listPageHTML;

    const dataContainer = document.getElementById('data-container');
    const pagination = document.getElementById('pagination');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');
    const pageNumbers = document.getElementById('page-numbers');
    const pageLinks = document.querySelectorAll('.page-link');
    
    // const cards = Array.from(dataContainer.getElementsByClassName('card'));
    
    console.log(typeof totalPages);
    let currentPage = 1;
    
    //display cards fpr specific page
    



    function displayPage(page){
        const startIndex = (page - 1) * cardsPerPage;
        const endIndex = startIndex + cardsPerPage;
        cards.forEach((card, index) => {
            if (index >= startIndex && index < endIndex) {
                card.style.display = 'block';
            }else {
                card.style.display = 'none';
            }
        });
    }
    
    
    // update pagination buttons and page numbers
    
    function updatePagination() {
        pageNumbers.textContent = `Page ${currentPage} of ${totalPages}`;
        prevButton.disabled = currentPage === 1;
        nextButton.disabled = currentPage === totalPages;
        pageLinks.forEach((link) => {
            const page = parseInt(link.getAttribute('data-page'));
            link.classList.toggle('active', page === currentPage);
        });
    }
    
    prevButton.addEventListener('click', () => {
        if (currentPage > 1) {
            currentPage--;
            displayPage(currentPage);
            updatePagination();
        }
    });

    nextButton.addEventListener('click', () => {
        if (currentPage < totalPages) {
            currentPage = currentPage + 1;
            displayPage(currentPage);
            updatePagination();
        }
    
    });
    
    pageLinks.forEach((link) =>{
        /*e = event
        When an event occurs (such as a click), the browser creates an event object and passes it to the event handler.
    The event object contains information about the event, such as the type of event, the target element, mouse coordinates, key codes (for keyboard events), and more.
        */ 
        link.addEventListener('click', (e) =>{
            e.preventDefault();
            const page = parseInt(link.getAttribute('data-page'));
            if (!isNaN(page) && page !== currentPage && page >= 1 && page <= totalPages) {
                currentPage = page; 
                displayPage(currentPage);
                updatePagination();
            

            }
        });
    });

    
    displayPage(currentPage);
    updatePagination();


   


});
