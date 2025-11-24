// Esperar a que el DOM esté completamente cargado
document.addEventListener('DOMContentLoaded', function() {

    const p = document.querySelector(".nosotros");
    const contenedor_menu = document.querySelector("#contenedor_menu");
    const boton_buscar = document.querySelector("#boton_buscar");
    const boton_agregar = document.querySelector("#boton_agregar");
    const buscar_form = document.querySelector("#buscar_form");
    const traer_perros = document.querySelector("#btn_traer_perros");
    const lista_perros = document.querySelector("#lista_perros");
    const agregar_form = document.querySelector("#agregar_form");

    // Modo oscuro
    const toggleSwitch = document.querySelector('#checkbox');
    const themeText = document.querySelector('.theme-mode');

    // Verificar si hay una preferencia guardada
    const currentTheme = localStorage.getItem('theme') ? localStorage.getItem('theme') : null;

    if (currentTheme) {
        document.documentElement.setAttribute('data-theme', currentTheme);
        
        if (currentTheme === 'dark') {
            toggleSwitch.checked = true;
            themeText.textContent = 'Modo Claro';
        }
    }

    // Función para cambiar el tema
    function switchTheme(e) {
        if (e.target.checked) {
            document.documentElement.setAttribute('data-theme', 'dark');
            localStorage.setItem('theme', 'dark');
            themeText.textContent = 'Modo Claro';
        } else {
            document.documentElement.setAttribute('data-theme', 'light');
            localStorage.setItem('theme', 'light');
            themeText.textContent = 'Modo Oscuro';
        }    
    }

    // Evento para cambiar el tema
    toggleSwitch.addEventListener('change', switchTheme);
    
    // Funciones para activar formularios
    function activar_form_agregar() {
        contenedor_menu.style.display = "none";
        agregar_form.style.display = "flex";
    }
    boton_agregar.addEventListener("click", activar_form_agregar);

    function activar_form_buscar() {
        contenedor_menu.style.display = "none";
        buscar_form.style.display = "flex";
    }
    boton_buscar.addEventListener("click", activar_form_buscar);


    function mostrarPerros(perros) {
        lista_perros.innerHTML = perros.map(p =>
        `<div class="perro">
        <h3>${p.nombre}</h3>
        <h5>${p.descripcion}</h5>
        <h6>${p.ubicacion.barrio} - ${p.fecha}</h6>
        <img src="${p.imagen}" alt="Imagen de ${p.nombre}">
        </div>`
        ).join("") + `<button class="volver">Volver</button>`;
        setupVolverButtons();
    }

    // MÉTODO GET MASCOTAS POR BARRIO
    async function traer_perros_barrio(){
        const perro_barrio = document.querySelector("#barrio").value;

        buscar_form.style.display = "none";
        p.style.display = "none";
        lista_perros.classList.remove("hidden");

        try{
            const API_URL = window.API_URL || '';
        const respuesta = await fetch(`${API_URL}/api/mascotas/buscar?ubicacion=${perro_barrio}`);
            if (!respuesta.ok) throw new Error("Error al traer los perros");
            
            const perros = await respuesta.json();

            mostrarPerros(perros);
        
        } catch (error) {
            console.error(error);
            lista_perros.innerHTML = "<p>Error al obtener los perros 😕</p>";
        }
    }

    traer_perros.addEventListener("click", traer_perros_barrio);


    // Función para volver al menú principal
    function volverAlMenu() {
        agregar_form.style.display = "none";
        buscar_form.style.display = "none";
        lista_perros.classList.add("hidden");
        contenedor_menu.style.display = "flex";
        p.style.display = "block";
    }
    
    function setupVolverButtons() {
        document.querySelectorAll(".volver").forEach(btn => {
            btn.addEventListener("click", function(e) {
                e.preventDefault();
                volverAlMenu();
            });
        });
    }

    // Configurar event listeners para los botones "Volver" iniciales
    setupVolverButtons();
    
    console.log("Event listeners configurados correctamente");


    // MÉTODO POST MASCOTA
    
    async function guardar_perro(e) {
        e.preventDefault();
    
        const descripcionInput = document.querySelector("#descripcion"); 
        const ubicacionInput = document.querySelector("#ubicacion");
        const ubicacionErrorSpan = document.querySelector("#ubicacion-error");
        const imagenInput = document.querySelector("#imagen");
        const imagenErrorSpan = document.querySelector("#imagen-error");

        // Limpiar mensajes de error previos
        ubicacionErrorSpan.textContent = "";
        imagenErrorSpan.textContent = "";
        descripcionInput.style.borderColor = "";
        ubicacionInput.style.borderColor = "";

        const descripcion = descripcionInput.value;
        const ubicacion = ubicacionInput.value;

        const file = imagenInput.files[0];
    
      // Validaciones básicas
        if (!descripcion) { descripcionInput.style.borderColor = "red"; }
        if (!ubicacion) { ubicacionInput.style.borderColor = "red"; }

        if (!file) {
            imagenErrorSpan.textContent = "Por favor, selecciona una imagen.";
            return;
        }
    
      // Leer la imagen como Base64 (DataURL) para enviar en JSON
        const toDataURL = (file) => new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = () => resolve(reader.result);
        reader.onerror = reject;
        reader.readAsDataURL(file);
    });
    
        const imagenBase64 = await toDataURL(file);
    
      // Construir el payload que el backend puede esperar
        const payload = {
        nombre: "Perro encontrado", // valor por defecto si backend lo requiere
        descripcion,
        ubicacion: { barrio: ubicacion }, // si el backend espera objeto { barrio }
        fecha: new Date().toISOString(),
        imagen: imagenBase64
    };
    
    try {
        console.log("Enviando mascota (JSON)...", { descripcion, ubicacion, size: file.size });
        const API_URL = window.API_URL || '';
        const res = await fetch(`${API_URL}/api/mascotas`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
        });
    
        const contentType = res.headers.get('content-type') || '';
        const isJson = contentType.includes('application/json');
        const data = isJson ? await res.json() : await res.text();
        
        if (!res.ok) {
            let msg = "No se pudo guardar la mascota";
    
            // Intentar obtener JSON aunque el Content-Type sea incorrecto
            let parsed = null;
            if (isJson && typeof data === 'object') {
                parsed = data;
            } else if (typeof data === 'string') {
                try { parsed = JSON.parse(data); } catch { /* ignore parse errors */ }
            }
    
            // Extraer mensaje del servidor si está disponible
            const serverMsg = parsed && (parsed.message || parsed.detail || parsed.error);
            const rawText = typeof data === 'string' ? data : '';
    
            // Para cualquier 400, preferir el mensaje del backend; si no, usar el específico de ubicación
            if (res.status === 400) {
                // Verificar si el mensaje del backend es genérico "Bad Request"
                const isGenericBadRequest = (serverMsg && serverMsg.toLowerCase().includes('bad request')) ||
                                            (rawText && rawText.toLowerCase().includes('bad request'));

                if (isGenericBadRequest || (!serverMsg && !rawText)) {
                    msg = `La ubicación "${ubicacion}" no existe. Por favor, verifica el nombre del barrio.`;
                } else {
                    msg = serverMsg || rawText; // Usar el mensaje del backend si no es genérico
                }
                ubicacionInput.style.borderColor = "red";
            }

            // Si el backend lanza RuntimeException y responde 500 con texto relacionado a ubicación
            if (res.status === 500) {
                const bodyStr = (rawText || JSON.stringify(parsed ?? {})).toLowerCase();
                const mencionaUbicacion = bodyStr.includes('ubicacion') || bodyStr.includes('ubicación');
                const mencionaValida = bodyStr.includes('valida') || bodyStr.includes('válida');
                if (mencionaUbicacion && mencionaValida) {
                    msg = serverMsg || rawText || `La ubicación "${ubicacion}" no existe. Por favor, verifica el nombre del barrio.`;
                    ubicacionInput.style.borderColor = "red";
                }
            }
            console.error("Error al agregar el perro:", parsed ?? data, "status:", res.status);
            ubicacionErrorSpan.textContent = msg;
            return;
        }
    
        console.log("Perro agregado:", data);
        alert("Perro agregado con éxito 🐕");
    
        // Limpiar formulario
        document.querySelector("#descripcion").value = "";
        document.querySelector("#ubicacion").value = "";
        imagenInput.value = "";
        
        // Opcional: volver al menú tras guardar
        // volverAlMenu();
    } catch (err) {
        console.error("Error de red al guardar:", err);
        alert("Error de red al guardar: " + err.message);
    }
    }

agregar_form.addEventListener("submit", guardar_perro);

});


