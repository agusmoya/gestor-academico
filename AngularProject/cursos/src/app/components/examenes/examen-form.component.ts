import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Asignatura } from 'src/app/models/asignatura';
import { Examen } from 'src/app/models/examen';
import { Pregunta } from 'src/app/models/pregunta';
import { ExamenService } from 'src/app/services/examen.service';
import Swal from 'sweetalert2';
import { CommonFormComponent } from '../common-form.component';

@Component({
  selector: 'app-examen-form',
  templateUrl: './examen-form.component.html',
  styleUrls: ['./examen-form.component.css'],
})
export class ExamenFormComponent
  extends CommonFormComponent<Examen, ExamenService>
  implements OnInit {

  asignaturasPadre: Asignatura[] = [];
  asignaturasHija: Asignatura[] = [];
  errorPreguntas: string;

  constructor(service: ExamenService, router: Router, route: ActivatedRoute) {
    super(service, router, route);
    this.titulo = 'Crear Examen';
    this.model = new Examen();
    this.nombreModel = Examen.name;
    this.redirect = '/examenes';
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const id: number = +params.get('id');
      if (id) {
        this.service.ver(id).subscribe((model) => {
          this.model = model;
          this.titulo = 'Editar ' + this.nombreModel;
          this.cargarHijas();
          // == aunque la de arriba es más optima ya que va al backend ni hace consulta a la BD
          // this.service.findAllAsignaturas().subscribe(asignaturas => {
          //   this.asignaturasHija = asignaturas
          //   .filter(a => a.asignaturaPadre && a.asignaturaPadre.id === this.model.asignaturaPadre.id);
          // });

        });
      }
    });

    this.service.findAllAsignaturas()
    .subscribe(asignaturas =>
      this.asignaturasPadre = asignaturas.filter(asignatura => !asignatura.asignaturaPadre));
  }

  public crear(): void {
    if (this.model.preguntas.length == 0) {
      this.errorPreguntas = 'Examen debe tener preguntas';
      // Swal.fire(
      //   'Error Preguntas',
      //   'Examen debe tener preguntas',
      //   'error'
      // );
      return;
    }
    this.errorPreguntas = undefined;
    this.eliminarPreguntasVacias();
    super.crear();
  }

  public editar(): void {
    if (this.model.preguntas.length == 0) {
      this.errorPreguntas = 'Examen debe tener preguntas';
      // Swal.fire(
      //   'Error Preguntas',
      //   'Examen debe tener preguntas',
      //   'error'
      // );
      return;
    }
    this.errorPreguntas = undefined;
    this.eliminarPreguntasVacias();
    super.editar();
  }

  cargarHijas(): void {
    this.asignaturasHija = this.model.asignaturaPadre ? this.model.asignaturaPadre.asignaturasHijas : [];
    // console.log(this.model.asignaturaPadre);
    // console.log(this.asignaturasHija);
  }

  compararAsignatura(a1: Asignatura, a2: Asignatura): boolean {
    if (a1 === undefined && a2 === undefined) {
      return true;
    }

    if (a1 === null || a2 === null ||a1 === undefined || a2 === undefined) {
      return false;
    }

    if (a1.id === a2.id) {
      return true;
    }
  }

  agregarPregunta():void{
    this.model.preguntas.push(new Pregunta());
  }

  asignarTexto(pregunta: Pregunta, event: any): void {
    pregunta.enunciado = event.target.value as string;
    console.log(this.model);

  }

  eliminarPregunta(pregunta): void {
    this.model.preguntas = this.model.preguntas.filter(p => pregunta.enunciado !== p.enunciado);
  }

  eliminarPreguntasVacias(): void {
    this.model.preguntas = this.model.preguntas.filter(p => p.enunciado != null && p.enunciado.length > 0);
  }

}
