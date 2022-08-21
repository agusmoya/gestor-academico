import { Injectable, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import Swal from 'sweetalert2';
import { Generic } from '../models/generic';
import { CommonService } from '../services/common.service';

@Injectable()
export abstract class CommonListarComponent<E extends Generic, S extends CommonService<E>> implements OnInit {

  titulo: string;
  lista: E[];
  nombreModel:string;

  totalRegistros = 0;
  paginaActual = 0;
  totalPorPagina = 4;
  pageSizeOptions: number[] = [3, 5, 10, 25, 100];

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(protected service: S) { }

  ngOnInit(): void {
    this.calcularRangos();
  }

  paginar(event: PageEvent): void {
    this.paginaActual = event.pageIndex;
    this.totalPorPagina = event.pageSize;
    this.calcularRangos();
  }

  private calcularRangos() {
    this.service.listarPaginas(this.paginaActual.toString(), this.totalPorPagina.toString())
                .subscribe(paginacion => {
                  this.lista = paginacion.content as E[];
                  this.totalRegistros = paginacion.totalElements as number;
                  if (this.paginator) {
                    this.paginator._intl.itemsPerPageLabel = 'Registros por página: ';
                  }
                });
  }

  public eliminar(entity: E):void {
    Swal.fire({
      title: 'Atención:',
      text: `Seguro que desea eliminar a ${entity.nombre}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!'
    }).then((result) => {
      if (result.isConfirmed) {
          this.service.eliminar(entity.id).subscribe(()=>{
          this.calcularRangos();
          Swal.fire('Eliminado', `${this.nombreModel} ${entity.nombre} eliminado con éxito`, 'success');
        });
      }
    });
  }

}
