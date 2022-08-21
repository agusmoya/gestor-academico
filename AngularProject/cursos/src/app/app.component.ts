import { Component } from '@angular/core';

// ESTO ES UN DECORADOR CON LA METADATA
@Component({
  selector: 'app-root',//SELECTOR EN INDEX.HTML
  templateUrl: './app.component.html', //ESTO INDICA LA VISTA O LA PLANTILLA
  styleUrls: ['./app.component.css'] //ESTILOS, QUE PUEDEN SER MUCHOS O UNO. ESTOS SON UNICOS PARA CADA COMPONENTE, YA QUE NO AFECTAN A LOS DEMAS.
})
export class AppComponent {
  title = 'cursos'; // ESTE ES UN DATO QUE SE COMPARTE CON app.component.html EN DONDE SE IMPRMIME CON {{title}} (extrapolacion de string)
}
