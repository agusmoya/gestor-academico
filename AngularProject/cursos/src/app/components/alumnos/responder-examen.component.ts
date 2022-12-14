import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Alumno } from 'src/app/models/alumno';
import { Curso } from 'src/app/models/curso';
import { Examen } from 'src/app/models/examen';
import { Respuesta } from 'src/app/models/respuesta';
import { AlumnoService } from 'src/app/services/alumno.service';
import { CursoService } from 'src/app/services/curso.service';
import { RespuestaService } from 'src/app/services/respuesta.service';
import Swal from 'sweetalert2';
import { ResponderExamenModalComponent } from './responder-examen-modal.component';
import { VerExamenModalComponent } from './ver-examen-modal.component';

@Component({
  selector: 'app-responder-examen',
  templateUrl: './responder-examen.component.html',
  styleUrls: ['./responder-examen.component.css']
})
export class ResponderExamenComponent implements OnInit {

  alumno: Alumno;
  curso: Curso;
  examenes: Examen[] = [];
  mostrarColumnasExamenes = ['id', 'nombre', 'asignaturas', 'preguntas', 'responder', 'ver'];
  pageSizeOptions = [3, 5, 10, 50];
  dataSource: MatTableDataSource<Examen>;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private route: ActivatedRoute,
              private alumnoService: AlumnoService,
              private cursoService: CursoService,
              private respuestaService: RespuestaService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = +params.get('id');
      this.alumnoService.ver(id).subscribe(alumno => {
        this.alumno = alumno;
        this.cursoService.obtenerCursoPorAlumnoId(this.alumno).subscribe(
          curso => {
            this.curso = curso;
            this.examenes = (curso && curso.examenes) ? curso.examenes : [];
            this.dataSource = new MatTableDataSource<Examen>(this.examenes);
            this.dataSource.paginator = this.paginator;
            this.paginator._intl.itemsPerPageLabel = 'Registros por p??gina:';
          }
        );
      });
    });
  }

  responderExamen(examen: Examen): void {
    const modalRef = this.dialog.open(ResponderExamenModalComponent, {
      width: '750px',
      data: {curso: this.curso, alumno: this.alumno, examen: examen}
    });

    modalRef.afterClosed().subscribe((respuestasMap: Map<Number, Respuesta>) => {
      console.log('modal responder examen ha sido enviado y cerrado');
      console.log(respuestasMap);
      if (respuestasMap) {
        // el metodo Array.from() crea una nueva instancia de Array a partir
        // de un objeto iterable
        const respuestas: Respuesta[] = Array.from(respuestasMap.values());
        this.respuestaService.crear(respuestas).subscribe(rs => {
          examen.respondido = true;
          Swal.fire(
            'Enviadas:',
            'Preguntas enviadas con ??xito',
            'success'
          );
          console.log(rs);//mostramos a modo de debug, las respuestas emitidas luego de pasar por el backend

        });
      }

    });
  }

  verExamen(examen: Examen): void {
    this.respuestaService.obtenerRespuestasPorAlumnoPorExamen(this.alumno, examen)
    .subscribe(rs => {
      const modalRef = this.dialog
      .open(VerExamenModalComponent,
        {width: '750px',
         data: {curso: this.curso, examen: examen, respuestas: rs}
        });

        modalRef.afterClosed().subscribe(() => {
          console.log('Modal ver examen cerrado');
        });
    });

  }

}
