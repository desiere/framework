/*
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 * 
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 * 
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.frameworkdemoiselle.internal.processor;

import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.BeanManager;

import br.gov.frameworkdemoiselle.annotation.Startup;

/**
 * Processor for a {@code @Startup} annotated method, making it comparable.
 *
 * @param <T>
 */
public class StartupProcessor<T> extends AnnotatedMethodProcessor<T> implements Comparable<StartupProcessor<T>> {

	public StartupProcessor(final AnnotatedMethod<T> annotatedMethod, final BeanManager beanManager) {
		super(annotatedMethod, beanManager);
	}

	@Override
	public int compareTo(final StartupProcessor<T> other) {
		int result = 0;
		Startup annotationThis = getAnnotatedMethod().getAnnotation(Startup.class);
		Startup annotationOther = other.getAnnotatedMethod().getAnnotation(Startup.class);
		if (annotationThis != null && annotationOther != null) {
			Integer orderThis = annotationThis.priority();
			Integer orderOther = annotationOther.priority();
			result = orderThis.compareTo(orderOther);
		}
		return result;
	}

}
