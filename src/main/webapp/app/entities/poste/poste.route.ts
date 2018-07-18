import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Poste } from 'app/shared/model/poste.model';
import { PosteService } from './poste.service';
import { PosteComponent } from './poste.component';
import { PosteDetailComponent } from './poste-detail.component';
import { PosteUpdateComponent } from './poste-update.component';
import { PosteDeletePopupComponent } from './poste-delete-dialog.component';
import { IPoste } from 'app/shared/model/poste.model';

@Injectable({ providedIn: 'root' })
export class PosteResolve implements Resolve<IPoste> {
    constructor(private service: PosteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((poste: HttpResponse<Poste>) => poste.body));
        }
        return of(new Poste());
    }
}

export const posteRoute: Routes = [
    {
        path: 'poste',
        component: PosteComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Postes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poste/:id/view',
        component: PosteDetailComponent,
        resolve: {
            poste: PosteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Postes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poste/new',
        component: PosteUpdateComponent,
        resolve: {
            poste: PosteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Postes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poste/:id/edit',
        component: PosteUpdateComponent,
        resolve: {
            poste: PosteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Postes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const postePopupRoute: Routes = [
    {
        path: 'poste/:id/delete',
        component: PosteDeletePopupComponent,
        resolve: {
            poste: PosteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Postes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
