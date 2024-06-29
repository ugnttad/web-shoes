

document.querySelectorAll('.playPause').forEach((btn) => {
    btn.addEventListener('click', ()=> {
        const btn_VideoId =  btn.dataset.videoId;
        document.querySelectorAll('.video-trailer').forEach((link) => {
            const videoId = link.dataset.videoId;
            if (btn_VideoId === videoId) {
                if (link.paused) {
                    btn.innerHTML = `<i class="situation-video fa fa-pause " aria-hidden="true"></i>`;
                    link.play();
                } else {
                    btn.innerHTML =`<i class="situation-video fa fa-play " aria-hidden="true"></i>`;
                    link.pause();
                }
            }
        });
    });
});


document.querySelectorAll('.mute').forEach((btn) => {
    btn.addEventListener('click', ()=> {
        const btn_VideoId =  btn.dataset.videoId;
        document.querySelectorAll('.video-trailer').forEach((link) => {
            const videoId = link.dataset.videoId;
            if (btn_VideoId === videoId) {
                link.muted = !link.muted;
                btn.classList.toggle("muted");
            }
        });
    });
});