import { Component, OnInit } from '@angular/core';
import { BASE_ENDPOINT } from 'src/app/config/app';
import { Alumno } from 'src/app/models/alumno';
import { AlumnoService } from 'src/app/services/alumno.service';
import { CommonListarComponent } from '../common-listar.component';

@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css']
})
export class AlumnosComponent extends CommonListarComponent<Alumno, AlumnoService> implements OnInit {

  baseEndPoint = BASE_ENDPOINT + '/alumnos';

  constructor(service: AlumnoService) {
    super(service);
    this.titulo = 'Listado de Alumnos';
    this.nombreModel = Alumno.name; // == 'Alumno'
  }

}

  // public titulo: string = 'Listado de Alumnos';
  // ==
  // titulo = 'Listado de Alumnos';
  // alumnos: Alumno[];

  // paginacion
  // totalRegistros = 0;
  // paginaActual = 0;
  // totalPorPagina = 4;
  // pageSizeOptions: number[] = [3, 5, 10, 25, 100];

  // @ViewChild(MatPaginator) paginator: MatPaginator;

  //AQUI PODEMOS INYECTAR OBJETOS COMO UN SERVICE
  // constructor(private service: AlumnoService) { }

  // AQUI PODEMOS INICIALIZAR LOS OBJETOS INYECTADOS EN EL CONTRUCTOR.
  // TAMBIEN OBJETOS COMO EL htttpClient PARA COMUNICARNOS MEDIANTE APIREST AL BACKEND
  // Y ASI, SUSCRIBIRNOS A ESTOS FLUJOS REACTIVOS, A LOS OBSERVABLES, OBTENER DATOS Y
  // PASARSELOS A LA VISTA MEDIANTE ATRIBUTOS, ETC
  // siempre que queramos comunicarnos al backend lo hacemos dentro del metodo ngOnInit,
  // y todos los datos que queramos mostrar cuando se inicializa el componente
  // ngOnInit(): void {
  //   this.calcularRangos();
  // }

  // paginar(event: PageEvent): void {
  //   this.paginaActual = event.pageIndex;
  //   this.totalPorPagina = event.pageSize;
  //   this.calcularRangos();
  // }

  // private calcularRangos() {
    // const paginaActual = this.paginaActual+''; // +'' : conversion a string
     // const totalPorPagina = this.totalPorPagina+'';

     // lo que figura dentro del subscibe se lo conoce como "NOMBRE OBSERVADOR": es cualquier
     // código que queramos implementar para hacer algo con los datos que se emiten en este
     // flujo (ya que devuelve un Observable de Alumno[])
     // this.service.listar().subscribe(alumnos => this.alumnos = alumnos);
  //   this.service.listarPaginas(this.paginaActual.toString(), this.totalPorPagina.toString())
  //               .subscribe(paginacion => {
  //                 this.alumnos = paginacion.content as Alumno[];
  //                 this.totalRegistros = paginacion.totalElements as number;
  //                 this.paginator._intl.itemsPerPageLabel = 'Registros por página: ';
  //               });
  // }

  // public eliminar(alumno: Alumno):void {
  //   Swal.fire({
  //     title: 'Atención:',
  //     text: `Seguro que desea eliminar a ${alumno.nombre}`,
  //     icon: 'warning',
  //     showCancelButton: true,
  //     confirmButtonColor: '#3085d6',
  //     cancelButtonColor: '#d33',
  //     confirmButtonText: 'Si, eliminar!'
  //   }).then((result) => {
  //     if (result.isConfirmed) {
  //         this.service.eliminar(alumno.id).subscribe(()=>{
           // this.alumnos = this.alumnos.filter(a => a !== alumno);
  //         this.calcularRangos();
  //         Swal.fire('Eliminado', `Alumno ${alumno.nombre} eliminado con éxito`, 'success');
  //       });
  //     }
  //   });
  // }
